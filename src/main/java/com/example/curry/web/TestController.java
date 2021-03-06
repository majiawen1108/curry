package com.example.curry.web;

import com.example.curry.model.PageListResult;
import com.example.curry.model.TestUser;
import com.example.curry.service.TestService;
import com.example.curry.utils.RequestParms;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

/**
 * @author jw.ma
 * @title: TestController
 * @description: TODO
 * @date 2022/4/15 10:22
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/queryUserList")
    public List<TestUser> queryUserList(@RequestBody(required = false) TestUser testUser){
        return testService.queryUserList(testUser);
    }

    @RequestMapping(value = "/queryUserById")
    public TestUser queryUserById(HttpServletRequest request){
        return testService.queryUserById(request.getParameter("id"));
    }

    @RequestMapping(value = "/queryUserListByPage",method = RequestMethod.POST)
    public PageInfo<TestUser> queryUserListByPage(@RequestBody(required = false) RequestParms<TestUser> testUser){
        return testService.queryUserListByPage(testUser);
    }

    @RequestMapping(value = "/queryUserListWithPage",method = RequestMethod.POST)
    public PageListResult queryUserListWithPage(@RequestBody(required = false) RequestParms<TestUser> requestParms){
        List<TestUser> testUsers = testService.queryUserListWithPage(requestParms);
        return new PageListResult<>(testUsers);
    }

    @RequestMapping(value = "/queryUserList1WithPage",method = RequestMethod.POST)
    public PageInfo<TestUser> queryUserList1WithPage(@RequestBody(required = false) RequestParms<TestUser> requestParms){
        List<TestUser> testUsers = testService.queryUserListWithPage(requestParms);
        return new PageInfo<>(testUsers);
    }

    @RequestMapping(value = "/testFiFo",method = RequestMethod.POST)
    public void testFiFo(HttpServletRequest request){
        testService.testFiFo1();
    }
}
