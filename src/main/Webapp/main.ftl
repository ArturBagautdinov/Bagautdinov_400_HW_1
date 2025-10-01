<#include "base.ftl">

<#macro title>Кабинет</#macro>

<#macro content>
<h3>
    Привет, ${username}! Успешный вход
    <br>
    Session ID = ${sessionId}
    <br>
    Cookie user = ${cookieUser}
</h3>

<a href="/user">Пользователи</a>
<br>
<a href="/logout">Выйти</a>
</#macro>