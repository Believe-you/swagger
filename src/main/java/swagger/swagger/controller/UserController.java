package swagger.swagger.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import swagger.swagger.entity.User;

import java.math.BigDecimal;
import java.util.*;

@Api(value = "User-API",description = "这是用户接口详细信息的描述")//value:如果controller类名为UserController，那么此处该名称叫User-API。description：接口详细描述，建议不要超过25个字。
@RestController
@RequestMapping("/test")
public class UserController {
    static Map<String, User> users = Collections.synchronizedMap(new HashMap<String, User>());


    @ApiOperation(value = "获取用户列表",notes = "根据url的id来获取用户详细信息，返回List<User>类型用户信息的JSON")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful — 请求已完成")
//            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
//            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
//            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
//            @ApiResponse(code = 500, message = "服务器不能完成请求")
    }
    )
    @RequestMapping(value = {""}, method= RequestMethod.GET)
    public List<User> getUserList() {
        List<User> r = new ArrayList<User>(users.values());
        Map<String, Object> ret=  new HashMap<>();
        ret.put("users", r);
        ret.put("timstamp", System.currentTimeMillis());
        ret.put("price", BigDecimal.ZERO);
        ret.put("date", new Date());
        ret.put("query_id", "xxxx-id");
        return r;
    }

    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User"),//dataType最好加上包名一个参数的话只用这一个,多个就要s那个
            @ApiImplicitParam(name = "flag", value = "是否开启标志位", dataType = "Boolean"),//paramType如果是表单形式就写form
            @ApiImplicitParam(name = "version", value = "版本号", required = true, dataType = "string")

    })
    @RequestMapping(value = "", method = RequestMethod.POST)
    public BigDecimal postUser(User user, @RequestParam(defaultValue = "false") boolean flag, String version) {
        users.put(user.getId(), user);
        return BigDecimal.ZERO;
    }
}
