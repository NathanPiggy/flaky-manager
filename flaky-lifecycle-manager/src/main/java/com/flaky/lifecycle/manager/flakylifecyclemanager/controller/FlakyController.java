package com.flaky.lifecycle.manager.flakylifecyclemanager.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.flaky.lifecycle.manager.flakylifecyclemanager.model.Result;
import com.flaky.lifecycle.manager.flakylifecyclemanager.util.PageBean;
import com.flaky.persistence.mapper.FlakyHistoryMapper;
import com.flaky.persistence.mapper.FlakyMapper;
import com.flaky.persistence.model.Flaky;
import com.flaky.persistence.model.FlakyExample;
import com.flaky.persistence.model.FlakyHistory;
import com.flaky.persistence.model.FlakyHistoryExample;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping(value = "/flaky")
@Api(value = "FlakyController", tags = "Flaky接口列表")
@CrossOrigin
@Component
public class FlakyController{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private SqlSession sqlSession;

    private boolean externalSqlSession;

    @Autowired
    private FlakyMapper flakyMapper;

    @Autowired
    private FlakyHistoryMapper flakyHistoryMapper;

    @RequestMapping("/index")
    //return "/index" for windows and index for "linux"
    public String index(){
        return "/index";
    }

    @ApiOperation(value = "获取Flaky报表接口", notes = "获取Flaky报表接口", httpMethod = "POST")
    @RequestMapping("/getFlakyChart")
    @ResponseBody
    public Result<?> getFlakyChart(@RequestParam("projectId") String projectId ) {
        FlakyExample flakyUnhandleExample = new FlakyExample();
        flakyUnhandleExample.createCriteria().andProjectIdEqualTo(projectId).andFlakyStatusEqualTo(0);

        long countUnhandle = flakyMapper.countByExample(flakyUnhandleExample);

        FlakyExample flakyHandleCountExample = new FlakyExample();
        flakyHandleCountExample.createCriteria().andProjectIdEqualTo(projectId).andFlakyStatusEqualTo(1);
        long countHandle = flakyMapper.countByExample(flakyHandleCountExample);

        JSONObject result = new JSONObject();
        result.put("unhandleCount",countUnhandle);
        result.put("handleCount",countHandle);
        return Result.successData(result);
    }

    @ApiOperation(value = "获取Flaky列表接口", notes = "获取Flaky列表接口", httpMethod = "POST")
    @RequestMapping("/getFlakyList")
    @ResponseBody
    public Result<?> getFlakyList(@RequestParam("projectId") String projectId ,@RequestParam("flakyStatus") Integer flakyStatus , Integer currentPage, Integer pageSize ) {

        FlakyExample flakyExample = new FlakyExample();
        flakyExample.createCriteria().andProjectIdEqualTo(projectId).andFlakyStatusEqualTo(flakyStatus);
        List<Flaky> allItems = flakyMapper.selectByExample(flakyExample);

        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(currentPage, pageSize);

        FlakyExample flakyCountExample = new FlakyExample();
        flakyCountExample.createCriteria().andProjectIdEqualTo(projectId).andFlakyStatusEqualTo(0);

        long countNums = flakyMapper.countByExample(flakyCountExample);
        PageBean<Flaky> pageData = new PageBean<>(currentPage, pageSize, (int)countNums);
        pageData.setItems(allItems);
        return Result.successData(pageData);
    }

    @ApiOperation(value = "获取Flaky历史接口", notes = "获取Flaky历史接口", httpMethod = "POST")
    @RequestMapping("/getFlakyHistoryList")
    @ResponseBody
    public Result<?> getFlakyHistoryList(@RequestParam("flakyId") Integer flakyId,
                                         @RequestParam("projectId") String projectId, Integer currentPage, Integer pageSize) {

        FlakyHistoryExample flakyHistoryExample = new FlakyHistoryExample ();
        flakyHistoryExample.createCriteria().andProjectIdEqualTo(projectId).andFlakyIdEqualTo(flakyId);
        List<FlakyHistory> allItems = flakyHistoryMapper.selectByExample(flakyHistoryExample);

        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(currentPage, pageSize);

        FlakyHistoryExample flakyHistoryCountExample = new FlakyHistoryExample();
        flakyHistoryCountExample.createCriteria().andProjectIdEqualTo(projectId).andFlakyIdEqualTo(flakyId);

        long countNums = flakyHistoryMapper.countByExample(flakyHistoryCountExample);
        PageBean<FlakyHistory> pageData = new PageBean<>(currentPage, pageSize, (int)countNums);
        pageData.setItems(allItems);
        return Result.successData(pageData);
    }

    @ApiOperation(value = "获取Flaky环境参数接口", notes = "获取Flaky环境参数接口,返回的属性长度不定，需要前端遍历data里面所有的内容依次展示", httpMethod = "POST")
    @RequestMapping("/getFlakyEnvDetail")
    @ResponseBody
    public Result<?> getFlakyEnvDetail(@RequestParam("flakyHistoryId") Integer flakyHistoryId ) {

  /*      FlakyHistory flakyHistory = new FlakyHistory();
        flakyHistory.setEnvironmentDetail("{\n" +
                "    \"jdkversion\": \"1.5.0\",\n" +
                "    \"operationSystem\": \"window\"\n" +
                "}");*/

        FlakyHistoryExample flakyHistoryExample = new FlakyHistoryExample ();
        flakyHistoryExample.createCriteria().andFlakyHistoryIdEqualTo(flakyHistoryId);
        List<FlakyHistory> allItems = flakyHistoryMapper.selectByExample(flakyHistoryExample);
        FlakyHistory flakyHistory1 = allItems.stream().findFirst().orElse(new FlakyHistory());

        return Result.successData(JSONArray.parseArray(flakyHistory1.getEnvironmentDetail()));
    }

    @ApiOperation(value = "更新Flaky test状态接口", notes = "更新Flaky test状态接口", httpMethod = "POST")
    @RequestMapping("/updateFlakyTestStatus")
    @ResponseBody
    public Result<?> updateFlakyTestStatus(@RequestParam("flakyId") Integer flakyId,
                               @RequestParam("flakyStatus") int flakyStatus) {
        Flaky flaky = new Flaky();
        flaky.setFlakyId(flakyId);
        flaky.setFlakyStatus(flakyStatus);
        int successCount = flakyMapper.updateByPrimaryKeySelective(flaky);

        return successCount > 0 ? Result.success(): Result.fail();
    }

}
