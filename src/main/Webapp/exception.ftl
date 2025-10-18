<html lang="en">
<#include "base.ftl">

<#macro title>
    Exception details
</#macro>

<#macro content>
    <h3>Exception details:</h3>
    <strong>Request uri: ${uri}</strong>
    <strong>Status code: ${statusCode}</strong>
    <#if message??><strong>Message: ${message}</strong></#if>
</#macro>
</html>