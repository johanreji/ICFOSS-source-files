<#assign default_height=320>
<#assign el=args.htmlid?js_string>
<script type="text/javascript">
//<![CDATA[
   new Extras.dashlet.AuditApplication("${el}").setOptions(
   {
      componentId: "${instance.object.id}",

      <#-- escape quotes in user-entered parameters, just in case -->
      application: "${escape_quotes(args.application!'')}",
      valueFilter: "${escape_quotes(args.valueFilter!'')}",
      limit: "${escape_quotes(args.limit!'')}",
      rowsPerPage: "${escape_quotes(args.rowsPerPage!'10')}",
      pathFilter: "${escape_quotes(args.pathFilter!'')}",
      additionalQueryParams: "${escape_quotes(args.additionalQueryParams!'')}",

      <#-- these values are managed by the dashlet, but escape them anyways, just in case... -->
      show_id_column: "${escape_quotes(args.show_id_column!'show')}",
      show_user_column: "${escape_quotes(args.show_user_column!'show')}",
      show_time_column: "${escape_quotes(args.show_time_colum!'show')}",
      show_values_column: "${escape_quotes(args.show_values_column!'show')}",
      trim_audit_paths: "${escape_quotes(args.trim_audit_paths!'true')}"

   }).setMessages(${messages});

   <#-- dashlet resizer does not dynamically adjust the number of rows displayed on the page re: pagination. -->
   <#-- The number of rows can be configured in the dialog though, sufficient for now. -->
   <#-- future research : subscribe to rowsPerPageChange, "" etc...    -->
   new Alfresco.widget.DashletResizer("${el}", "${instance.object.id}").setOptions(
   {
      <#-- IE (as usual) needs apparently a default height, otherwise resizing may not work in some situations -->
      minDashletHeight: ${default_height}
   });

//]]>
</script>

<div class="dashlet audit-application-dashlet" id="${el}-dashlet">
  <div class=" container col-xs-3 well nav-pills nav-stacked nav"  style="min-height: 100vh;"   >
	<button class="btn btn-block" id="btn1" value="${el}-configDialog" >Manage Funding Types</button>
	

  </div>
  <div class=" container well col-xs-9" style="min-height: 100vh; " id="rightpane" >
	

  </div>
	<div id="createFTModal" class="modal fade" role="dialog" style="overflow-y:auto">
	

	</div>
</div>

<#-- for safety, escape double quotes in the arguments that will be passed to the instanciated dashlet component options, otherwise the json feed will be invalid. -->
<#-- Takes a string as parameter (cannot be null), and returns the string with double quotes escaped. If the parameter is the empty string, it will be returned as is. -->
<#function escape_quotes string>
   <#if string?has_content>
      <#-- note that the various backslashes are required to pass through the evaluation chain -->
      <#return string?replace('"',"\\\\\\\"",'r')>
   <#else>
      <#return string>
   </#if>
</#function>
