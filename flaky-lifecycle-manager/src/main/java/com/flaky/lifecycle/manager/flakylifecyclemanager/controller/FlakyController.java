package com.flaky.lifecycle.manager.flakylifecyclemanager.controller;

import com.alibaba.fastjson.JSONObject;
import com.flaky.lifecycle.manager.flakylifecyclemanager.dao.CommonDao;
import com.flaky.lifecycle.manager.flakylifecyclemanager.model.Result;
import com.flaky.lifecycle.manager.flakylifecyclemanager.util.PageBean;
import com.flaky.persistence.mapper.FlakyHistoryMapper;
import com.flaky.persistence.mapper.FlakyMapper;
import com.flaky.persistence.model.Flaky;
import com.flaky.persistence.model.FlakyExample;
import com.flaky.persistence.model.FlakyHistory;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping(value = "/flaky")
@Api(value = "FlakyController", tags = "Flaky接口列表")
@CrossOrigin
public class FlakyController extends CommonDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private SqlSession sqlSession;

    private boolean externalSqlSession;

    @Autowired
    private FlakyMapper flakyMapper;

    @Autowired
    private FlakyHistoryMapper flakyHistoryMapper;

    @RequestMapping("/index")
    public String index(){
        return "/index";
    }

    @ApiOperation(value = "获取Flaky报表接口", notes = "获取Flaky报表接口", httpMethod = "POST")
    @RequestMapping("/getFlakyChart")
    @ResponseBody
    public Result<?> getFlakyChart(@RequestParam("projectId") String projectId ) {
        FlakyExample flakyExample = new FlakyExample();
        flakyExample.createCriteria().andProjectIdEqualTo(projectId).andFlakyStatusEqualTo(0);

        long countUnhandle = flakyMapper.countByExample(flakyExample);
        flakyExample.createCriteria().andProjectIdEqualTo(projectId).andFlakyStatusEqualTo(1);
        long countHandle = flakyMapper.countByExample(flakyExample);

        JSONObject result = new JSONObject();
        result.put("unhandleCount",countUnhandle);
        result.put("handleCount",countHandle);
        return Result.successData(result);
    }

    @ApiOperation(value = "获取Flaky列表接口", notes = "获取Flaky列表接口", httpMethod = "POST")
    @RequestMapping("/getFlakyList")
    @ResponseBody
    public Result<?> getFlakyList(@RequestParam("projectId") String projectId ,@RequestParam("flakyStatus") Integer flakyStatus , Integer currentPage, Integer pageSize ) {
        Flaky flaky = new Flaky();
        flaky.setFlakyId(1);
        flaky.setClassName("com.graphql.study.demo.service.imp.UserServiceImpTest");
        flaky.setUnitTestName("getRandomNumber");
        flaky.setFlakyStatus(0);
        flaky.setDetectCount(10);
        flaky.setLastDetectTime(new Date());
        flaky.setLastSha1("feb62f765fafb2efad16295faaaa21f302c647d7");
        flaky.setProjectId("com.flaky.lifecycle.manager.flaky-lifecycle-manager");

        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(currentPage, pageSize);

        List<Flaky> allItems = new ArrayList<>();        //全部记录
        allItems.add(flaky);
        int countNums = 35;            //总记录数
        PageBean<Flaky> pageData = new PageBean<>(currentPage, pageSize, countNums);
        pageData.setItems(allItems);
        return Result.successData(pageData);
    }

    @ApiOperation(value = "获取Flaky历史接口", notes = "获取Flaky历史接口", httpMethod = "POST")
    @RequestMapping("/getFlakyHistoryList")
    @ResponseBody
    public Result<?> getFlakyHistoryList(@RequestParam("flakyId") Integer flakyId,
                                         @RequestParam("projectId") String projectId, Integer currentPage, Integer pageSize) {

        FlakyHistory flakyHistory = new FlakyHistory();
        flakyHistory.setFlakyId(1);
        flakyHistory.setClassName("com.graphql.study.demo.service.imp.UserServiceImpTest");
        flakyHistory.setUnitTestName("getRandomNumber");
        flakyHistory.setFlakyStatus(0);
        flakyHistory.setDetectCount(10);
        flakyHistory.setDetectTime(new Date());
        flakyHistory.setSha1("feb62f765fafb2efad16295faaaa21f302c647d7");
        flakyHistory.setProjectId("com.flaky.lifecycle.manager.flaky-lifecycle-manager");
        List<FlakyHistory> allItems = new ArrayList<>();        //全部记录
        allItems.add(flakyHistory);
        int countNums = 35;            //总记录数
        PageBean<FlakyHistory> pageData = new PageBean<>(currentPage, pageSize, countNums);
        pageData.setItems(allItems);
        return Result.successData(pageData);
    }

    @ApiOperation(value = "获取Flaky环境参数接口", notes = "获取Flaky环境参数接口,返回的属性长度不定，需要前端遍历data里面所有的内容依次展示", httpMethod = "POST")
    @RequestMapping("/getFlakyEnvDetail")
    @ResponseBody
    public Result<?> getFlakyEnvDetail(@RequestParam("flakyId") Integer flakyId,
                                       @RequestParam("projectId") String projectId ) {

        FlakyHistory flakyHistory = new FlakyHistory();
        flakyHistory.setEnvironmentDetail("{\n" +
                "    \"jdkversion\": \"1.5.0\",\n" +
                "    \"operationSystem\": \"window\"\n" +
                "}");
        return Result.successData(JSONObject.parseObject(flakyHistory.getEnvironmentDetail()));
    }

    @ApiOperation(value = "更新Flaky test状态接口", notes = "更新Flaky test状态接口", httpMethod = "POST")
    @RequestMapping("/updateFlakyTestStatus")
    @ResponseBody
    public Result<?> updateFlakyTestStatus(@RequestParam("flakyId") Integer flakyId,
                            @RequestParam("projectId") String projectId,
                               @RequestParam("flakyStatus") int flakyStatus) {

        return Result.success();
    }

}
