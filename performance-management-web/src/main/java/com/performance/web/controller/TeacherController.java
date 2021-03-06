package com.performance.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.performance.persist.common.JsonPage;
import com.performance.persist.common.JsonResult;
import com.performance.persist.domain.Person;
import com.performance.persist.domain.TeacherPerformance;
import com.performance.service.TeacherService;

@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {

    private static Pattern NAME_PATTERN = Pattern.compile("[A-Za-z0-9()\\-\\+\\*_\u4e00-\u9fa5]*");
    private static Pattern Number_PATTERN = Pattern.compile("[1-9][0-9]*");

    @Autowired
    private TeacherService teacherService;

    /**
     * 教师注册
     * */
    @RequestMapping("/teacherRegister")
    public ResponseEntity<JsonResult<Boolean>> teacherRegister(String teacherJSON) {
        JsonResult<Boolean> jr = new JsonResult<Boolean>();

        if (teacherJSON == null) {
            jr.setStatus(3); //3-参数为空
            jr.setMsg("参数为空");
            jr.setData(false);
        } else {
            Person person = JSON.parseObject("teacherJSON", Person.class);
            if (!Number_PATTERN.matcher(person.getId().toString()).matches()) {
                throw new RuntimeException("ID不符合格式要求");
            }

            if (!NAME_PATTERN.matcher(person.getName()).matches() || person.getName().length() < 0
                || person.getName().length() > 20) {
                throw new RuntimeException("名字不符合格式要求");
            }

            //if()
            try {
                Boolean result = teacherService.saveRegisterPerson(person);
                if (true == result) {
                    jr.setStatus(0);
                    jr.setMsg("注册成功");
                    jr.setData(result);
                } else {
                    jr.setStatus(1);
                    jr.setMsg("注册失败");
                    jr.setData(result);
                }
            } catch (Exception e) {
                jr.setStatus(2);
                jr.setMsg("系统异常");
                jr.setData(false);
            }

        }
        return new ResponseEntity<JsonResult<Boolean>>(jr, HttpStatus.OK);
    }

    /**
     * 教师登录
     * */
    @RequestMapping(value = "/teacherLogin")
    public ResponseEntity<JsonResult<Boolean>> teacherLogin(Long id, String password) {
        JsonResult<Boolean> jr = new JsonResult<Boolean>();
        if (null == id || null == password) {
            jr.setData(false);
            jr.setMsg("参数为空");
            jr.setStatus(3); //3-参数为空
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", id);
            map.put("password", password);
            try {
                Person person = teacherService.teacherLogin(map);
                if (null == person) {
                    jr.setStatus(1);
                    jr.setMsg("该用户不存在");
                    jr.setData(false);
                } else {
                    jr.setStatus(0);
                    jr.setMsg("登录成功");
                    jr.setData(true);
                }
            } catch (Exception e) {
                jr.setStatus(2);
                jr.setMsg("系统异常");
                jr.setData(false);
            }
        }
        return new ResponseEntity<JsonResult<Boolean>>(jr, HttpStatus.OK);
    }

    /**
     * 查看教师信息
     * */
    @RequestMapping("/getTeacherInfo")
    public ResponseEntity<JsonResult<Person>> getTeacherInfo(Long id) {
        JsonResult<Person> jr = new JsonResult<Person>();
        if (null == id) {
            jr.setStatus(3);
            jr.setMsg("参数为空");
            jr.setData(null);
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            try {
                Person person = teacherService.getTeacherInfo(map);
                if (null == person) {
                    jr.setStatus(2);
                    jr.setMsg("查询失败，无该用户");
                    jr.setData(null);
                } else {
                    jr.setStatus(0);
                    jr.setMsg("查询成功");
                    jr.setData(person);
                }
            } catch (Exception e) {
                jr.setStatus(2);
                jr.setMsg("系统异常");
                jr.setData(null);
            }
        }
        return new ResponseEntity<JsonResult<Person>>(jr, HttpStatus.OK);
    }

    /**
     * 修改密码
     * */
    @RequestMapping("/updatePassword")
    public ResponseEntity<JsonResult<Boolean>> updatePassword(Long id, String password) {
        JsonResult<Boolean> jr = new JsonResult<Boolean>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("password", password);
        try {
            String result = teacherService.updatePassword(map);
            if (result.equals("修改成功")) {
                jr.setData(true);
                jr.setMsg(result);
                jr.setStatus(0);
            } else if (result.equals("该用户不存在")) {
                jr.setData(false);
                jr.setMsg(result);
                jr.setStatus(3);
            } else {
                jr.setData(false);
                jr.setMsg(result);
                jr.setStatus(1);
            }
        } catch (Exception e) {
            jr.setData(false);
            jr.setMsg("系统异常");
            jr.setStatus(2);
        }
        return new ResponseEntity<JsonResult<Boolean>>(jr, HttpStatus.OK);
    }

    /**
     * 修改教师信息
     * */
    @RequestMapping("/updateTeacherInfo")
    public ResponseEntity<JsonResult<Boolean>> updateTeacherInfo(Person person) {
        JsonResult<Boolean> jr = new JsonResult<Boolean>();
        try {
            String result = teacherService.updateTeacherInfo(person);
            if (result.equals("修改成功")) {
                jr.setData(true);
                jr.setMsg(result);
                jr.setStatus(0);
            } else if (result.equals("该用户不存在")) {
                jr.setData(false);
                jr.setMsg(result);
                jr.setStatus(3);
            } else {
                jr.setData(false);
                jr.setMsg(result);
                jr.setStatus(1);
            }
        } catch (Exception e) {
            jr.setData(false);
            jr.setMsg("系统异常");
            jr.setStatus(2);
        }
        return new ResponseEntity<JsonResult<Boolean>>(jr, HttpStatus.OK);
    }

    /**
     * 教师录入绩效
     * */
    @RequestMapping(value = "/entryPerformance")
    public ResponseEntity<JsonResult<Boolean>> entryPerformance(TeacherPerformance teacherPerformance) {
        JsonResult<Boolean> jr = new JsonResult<Boolean>();
        if (null == teacherPerformance) {
            jr.setData(false);
            jr.setMsg("参数为空");
            jr.setStatus(3);
        } else {
            try {
                Boolean result = teacherService.saveTeacherPerformance(teacherPerformance);
                if (true == result) {
                    jr.setData(true);
                    jr.setMsg("绩效录入成功，待审核");
                    jr.setStatus(0);
                } else {
                    jr.setData(false);
                    jr.setMsg("绩效录入失败");
                    jr.setStatus(1);
                }
            } catch (Exception e) {
                jr.setData(false);
                jr.setMsg("系统异常");
                jr.setStatus(2);
            }
        }
        return new ResponseEntity<JsonResult<Boolean>>(jr, HttpStatus.OK);
    }

    /**
     * 总分排名
     * */
    @RequestMapping(value = "/totalRank")
    public ResponseEntity<JsonPage<List<Person>>> totalRank(Long id, Integer pageSize,
                                                            Integer pageNum) {
        JsonPage<List<Person>> jp = new JsonPage<List<Person>>();
        if (null == id) {
            jp.setData_list(null);
            jp.setMsg("参数为空");
            jp.setStatus(3);
        } else {
            int pSize = pageSize == null ? 20 : pageSize;
            int pNum = pageNum == null ? 1 : pageNum;
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", id);
            map.put("pSize", pSize);
            map.put("pNum", pNum);
        }
        return null;
    }

}
