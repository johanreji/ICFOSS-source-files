{
"Results" : [
		
<#list fundTypes as result>		
{
"fundCode" : "${result.fundCode}",
"fundName" : "${result.fundName}",
"fundDesc" : "${result.fundDesc}"
}<#if result_has_next>,</#if>
</#list>]
}


