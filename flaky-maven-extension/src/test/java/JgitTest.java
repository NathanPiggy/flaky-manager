import junit.framework.TestCase;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.*;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.patch.FileHeader;
import org.eclipse.jgit.patch.HunkHeader;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.AbstractTreeIterator;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JgitTest extends TestCase {

    String gitFilePath = "D:\\WorkSpace_Download\\graphql-demo-with-spring-boot";
    File root = new File(gitFilePath);
    Git git;
    Repository repository;

    @org.junit.Before
    public void init() {
        try {
            git = Git.open(root);
            repository = git.getRepository();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() throws Exception {
        init();
        RevWalk walk = new RevWalk(repository);
        List<RevCommit> commitList = new ArrayList<>();
        //获取最近提交的两次记录
        Iterable<RevCommit> commits = git.log().setMaxCount(2).call();
        for (RevCommit commit : commits) {
            commitList.add(commit);
            System.out.println("!------commit.getFullMessage():" + commit.getFullMessage());
            System.out.println("!------commit.getAuthorIdent().getWhen():" + commit.getAuthorIdent().getWhen());
        }

        if (commitList.size() == 2) {
            AbstractTreeIterator newTree = prepareTreeParser(commitList.get(0));
            AbstractTreeIterator oldTree = prepareTreeParser(commitList.get(1));
            List<DiffEntry> diff = git.diff().setOldTree(oldTree).setNewTree(newTree).setShowNameAndStatusOnly(true).call();

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            DiffFormatter df = new DiffFormatter(out);
            //设置比较器为忽略空白字符对比（Ignores all whitespace）
            df.setDiffComparator(RawTextComparator.WS_IGNORE_ALL);
            df.setRepository(git.getRepository());
            System.out.println("------------------------------start-----------------------------");
            //每一个diffEntry都是第个文件版本之间的变动差异
            for (DiffEntry diffEntry : diff) {
                //打印文件差异具体内容
                df.format(diffEntry);
                String diffText = out.toString("UTF-8");
                System.out.println("#------diffText=" + diffText);

                //获取文件差异位置，从而统计差异的行数，如增加行数，减少行数
                FileHeader fileHeader = df.toFileHeader(diffEntry);
                List<HunkHeader> hunks = (List<HunkHeader>) fileHeader.getHunks();
                int addSize = 0;
                int subSize = 0;
                for (HunkHeader hunkHeader : hunks) {
                    EditList editList = hunkHeader.toEditList();
                    for (Edit edit : editList) {
                        subSize += edit.getEndA() - edit.getBeginA();
                        addSize += edit.getEndB() - edit.getBeginB();

                    }
                }
                System.out.println("addSize=" + addSize);
                System.out.println("subSize=" + subSize);
                System.out.println("------------------------------end-----------------------------");
                out.reset();
            }

        }

    }

    public AbstractTreeIterator prepareTreeParser(RevCommit commit) {
        System.out.println("commit.getId()="+commit.getId());
        System.out.println("commit.getId().toString().split="+commit.getId().toString().split(" ")[1]);
        try (RevWalk walk = new RevWalk(repository)) {
            System.out.println("commit.getTree().getId()="+commit.getTree().getId());
            RevTree tree = walk.parseTree(commit.getTree().getId());

            CanonicalTreeParser oldTreeParser = new CanonicalTreeParser();
            try (ObjectReader oldReader = repository.newObjectReader()) {
                oldTreeParser.reset(oldReader, tree.getId());
            }

            walk.dispose();

            return oldTreeParser;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }


    /**统计非空白行数
     * @param content
     * @return
     */
    public int countAddLine(String content){
        char[] chars = content.toCharArray();
        int sum = 0;
        boolean notSpace = false;
        for(char ch: chars){
            if(ch =='\n' && notSpace){
                sum++;
                notSpace = false;
            }else if(ch > ' '){
                notSpace = true;
            }
        }
        //最后一行没有换行时，如果有非空白字符，则+1
        if(notSpace){
            sum++;
        }
        return sum;
    }

}

