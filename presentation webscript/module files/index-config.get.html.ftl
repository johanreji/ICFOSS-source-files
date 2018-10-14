

<!--data-toggle="modal" data-target="#createFTModal"-->
<#assign el=args.htmlid?js_string>
<div id="${el}-configDialog" class="configure">


<div class="row" >
				<div class="col-xs-12">
					<button id="btn2" class=" btn pull-right col-xs-3 greybg" data-toggle="modal" data-target="#createFTModal"  value="${el}-modalDialog" >Create New Funding Types</button>
				</div>
			</div>

			<div class="row">
				<div class="col-xs-12">
					<h4 style="text-align: left;border-bottom: 1px solid blue;line-height: 0;">
						
						<label style="background: white;display: inline;">Search Criteria</label>
					</h4>
				</div>
			</div>	
			
			<br>

			<div class="row">
				<form class="form-inline" method="" action="">
					
					<div class="col-xs-2 ">
						<label>Institution: </label>
					</div>
					
					
					<div id="selectDiv" class="col-xs-3 ">
						

								
							</div>
							
							<div class="col-xs-2 pull-right">
								<button type="button" class="btn pull-right greybg" id="search-btn" value="${el}-tableDialog">Search</button>
							</div>
						</form>
					</div>

				<!-- <div class="row">
					<div class="col-lg-12" >
						<div style="background-color: blue;">
							<p class="lead" style="color: white;">Available Funding Types</p	>
						</div>
						
					</div>
				</div>
			-->

			<div class="row">
				<div class="col-xs-12" style="margin-bottom: 5px;" >
						<div style="background-color: blue;">
							<label style="display:none; color:white; margin-top:5px;" id="status-label"></label>
						</div>
						
				</div>
			</div>
			
			<div class="row" style=" margin-top: 10px; padding-top: 10px; display: none;" id="data">
				<form method="GET" name="delete">
					
					
					<div class="col-xs-11">

						<table class=" table table-striped table-bordered" id="fundingTypeTable">

							<thead>
								<tr class="greybg">
									<th class="col-xs-1"></th>
									<th class="col-xs-3">Funding Type</th>
									<th class="col-xs-6">Description</th>
								</tr>
							</thead>
							<tbody>
								<!-- <tr>
									<td class="col-xs-1"><input class="checkbox" type="checkbox" name="delete"></td>
									<td class="col-xs-3"></td>
									<td class="col-xs-6"></td>
								</tr> -->
								
							</tbody>
							
						</table>
					</div>
					<div class="col-xs-1">
						<button type="button" class="btn btn-default pull-right">Delete</button>
					</div>
				</form>	
			</div>

</div>
