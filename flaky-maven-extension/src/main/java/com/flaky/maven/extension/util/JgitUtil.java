package com.flaky.maven.extension.util;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.errors.RepositoryNotFoundException;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.AbstractTreeIterator;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class JgitUtil {

    static Git git;
    static Repository repository;

    public static void init(){
        String gitFilePath = System.getProperty("user.dir");
        File root = new File(gitFilePath);
        try {

            git = Git.open(root);
            repository = git.getRepository();

        } catch (final RepositoryNotFoundException rnfe) {
            final File gitRoot = getGitRootIfItExistsInOneOfTheParentDirectories(new File(gitFilePath));
            try {
                git = Git.open(gitRoot);
            } catch (IOException e) {
                e.printStackTrace();
            }
            repository = git.getRepository();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static File getGitRootIfItExistsInOneOfTheParentDirectories(File candidateDir) {
        while (candidateDir != null && /* HACK ATTACK! Maybe.... */ !candidateDir.getName().equals("target")) {
            if (new File(candidateDir, ".git").isDirectory()) {
                return candidateDir;
            }
            candidateDir = candidateDir.getParentFile();
        }
        return null;
    }

    public static HashMap<String,String> getDiffFileList(){
        HashMap<String,String> diffFileMap = new HashMap<>();
        //获取最近提交的两次记录
        Iterable<RevCommit> commits = null;
        try {
            commits = git.log().setMaxCount(2).call();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
        int counter = 0 ;
        AbstractTreeIterator newTree = null;
        AbstractTreeIterator oldTree = null;

        for (RevCommit commit : commits) {
            diffFileMap.put(counter == 0 ? "currentSha1": "lastSha1",commit.getId().toString().split(" ")[1]);

            if(counter == 0){
                newTree =prepareTreeParser(commit);
            }else {
                oldTree =prepareTreeParser(commit);
            }

            counter ++;
        }

        try {
            List<DiffEntry> diff = git.diff().setOldTree(oldTree).setNewTree(newTree).setShowNameAndStatusOnly(true).call();
            for (DiffEntry diffEntry : diff) {
                String diffEntryStr = diffEntry.toString();
                String firstKeyword = "/";
                if(diffEntryStr.lastIndexOf("/") < 0){
                    firstKeyword = " ";
                }

                String fileName = diffEntryStr.substring(diffEntryStr.lastIndexOf(firstKeyword)+1,diffEntryStr.lastIndexOf("."));
                diffFileMap.put(fileName+"Test",fileName);

            }
        } catch (GitAPIException e) {
            e.printStackTrace();
        }

        return diffFileMap;
    }

 /*   public static void main(String[] args) {
        init();
        HashMap<String,String> hashMap =  getDiffFileList();
    }*/


    public static AbstractTreeIterator prepareTreeParser(RevCommit commit) {
        //System.out.println("commit.getId()="+commit.getId());
        //System.out.println("commit.getId().toString().split="+commit.getId().toString().split(" ")[1]);
        try (RevWalk walk = new RevWalk(repository)) {
            //System.out.println("commit.getTree().getId()="+commit.getTree().getId());
            RevTree tree = walk.parseTree(commit.getTree().getId());

            CanonicalTreeParser oldTreeParser = new CanonicalTreeParser();
            try (ObjectReader oldReader = repository.newObjectReader()) {
                oldTreeParser.reset(oldReader, tree.getId());
            }

            walk.dispose();

            return oldTreeParser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
