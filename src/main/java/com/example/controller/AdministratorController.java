package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.form.InsertAdministratorForm;
import com.example.service.AdministratorService;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 管理者登録画⾯を表⽰する処理を実装する.
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

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

}
