package com.hotelres.controller;

import com.hotelres.bean.Hadmin;
import com.hotelres.service.impl.AdminServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author scqzy
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Resource
    AdminServiceImpl adminService;

    @RequestMapping("/login")
    public void login(String aname, String apassword, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        System.out.println(aname + "--" + apassword);
        Hadmin hadmin = adminService.login(aname, apassword);
        System.out.println(hadmin);
        if (hadmin == null) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("<script>alert('登录失败，请检查用户名密码！');window.location='/adminLogin.jsp';</script>");
            response.getWriter().flush();
        } else {
            request.getSession().setAttribute("aname", aname);
            request.getRequestDispatcher(request.getContextPath() + "/admin.jsp").forward(request, response);
        }
    }
}
