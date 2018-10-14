
<#assign el=args.htmlid?js_string>
<div id="${el}-modalDialog" class="configure">
		
		<div class="modal-dialog">
			<div class="modal-content">
				
				<div class="modal-header" style="background-color: blue;">

					<button  class="close redclose" data-dismiss="modal" >X</button>
					
					<label style="color:white;">Create New Funding Types</label>
					
				</div>

				<div class="modal-body">
					<form class="form-horizontal" id="modalform" method="GET" >
						<div class="form-group">
							<label class="control-label col-xs-4 " style="text-align: left;">Funding Type Code</label>
							<div class="col-xs-4">
								<input type="text" name="f_code" class="form-control" maxlength="16">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-xs-4 " style="text-align: left;">Funding Type Name</label>
							<div class="col-xs-7">
								<input type="text" style="width:100%;" name="f_name" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-xs-4 " style="text-align: left;">Description</label>
							<div class="col-xs-7">
								<!-- <input type="text" class="form-control"> -->
								<textarea class="form-control" name="f_desc" form="modalform" style="resize: vertical;width:100%;"></textarea>
							</div>
						</div> 
						<div class="form-group">
							<label class="control-label col-xs-4 " style="text-align: left;">Institution</label>
							<div id ="multiselectDiv" class="col-xs-4">
								<select id="ms" multiple="multiple" name="f_inst" class="col-xs-12">
									
									<option>institution1</option>
									<option>institution2</option>
									<option>institution3</option>
									<option>institution4</option>
								</select>
							</div>
						</div> 
						
						
						<div class="form-group">
							<div class="col-xs-2 pull-right">
								<input id="btn3" type="button" class="btn greybg" value="Create">
							</div>
						</div>
					</form>
				</div>
				
			</div>
		</div>
		
	</div>
</div>
