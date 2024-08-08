package com.example.controller;

import jakarta.servlet.http.HttpSession;
import java.util.Objects;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 管理者登録画⾯を表⽰する処理を実装する.
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private HttpSession session;

    private static final String LOGIN_ERR_MSG_KEY = "loginErrMsg";
    private static final String LOGIN_ERR_MSG_VALUE = "メールアドレスまたはパスワードが不正です。";
    private static final String ADMINISTRATOR_NAME = "administratorName";

    /**
     * ログイン画面の表示.
     * 
     * @param form LoginForm
     * @return ログイン画面
     */
    @GetMapping("/")
    public String toLogin(LoginForm form) {
        return "administrator/login";
    }

    /**
     * ログイン処理.
     * 
     * <pre>
     * DBからレコードを取得できる場合は、従業員情報⼀覧ページにリダイレクトします。
     * DBからレコードを取得できない場合は、エラーメッセージを格納し、ログイン画面へフォワードします。
     * </pre>
     * 
     * @param form LoginForm
     * @param model Model
     * @return 従業員情報⼀覧ページ 又は ログイン画面
     */
    @PostMapping("/login")
    public String login(LoginForm form, Model model) {
        Administrator administrator =
                administratorService.login(form.getMailAddress(), form.getPassword());

        if (Objects.isNull(administrator)) {
            model.addAttribute(LOGIN_ERR_MSG_KEY, LOGIN_ERR_MSG_VALUE);
            return "administrator/login";
        }

        session.setAttribute(ADMINISTRATOR_NAME, administrator.getName());
        return "redirect:/employee/showList";
    }

    /**
     * 管理者登録画面の表示.
     * 
     * @param form InsertAdministratorForm
     * @return 管理者登録画面
     */
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form) {
        return "administrator/insert";
    }

    /**
     * 管理者情報を登録する.
     * 
     * @param form InsertAdministratorForm
     * @return ログイン画面へリダイレクト
     */
    @PostMapping("/insert")
    public String insert(InsertAdministratorForm form) {
        Administrator administrator = new Administrator();
        BeanUtils.copyProperties(form, administrator);
        administratorService.insert(administrator);
        return "redirect:/";
    }
}
