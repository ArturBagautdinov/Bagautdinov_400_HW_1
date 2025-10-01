<#include "base.ftl">

<#macro title>Регистрация</#macro>

<#macro content>
    <form method="post" action="/sign_up">
        Логин:
        <input type="text" name="login">
        Пароль:
        <input type="password" name="password">
        <br>
        <input type="submit" value="Зарегистрироваться">
    </form>
</#macro>