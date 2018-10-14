{
"Results" : [
<#list instValues as result>		
{
"instName" : "${result.instName}",
"instCode" : "${result.instCode}"
}<#if result_has_next>,</#if>
</#list>
]
}


