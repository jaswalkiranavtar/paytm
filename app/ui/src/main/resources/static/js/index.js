var dashboards = ["search.html", "activity.html"]

frameLoadedv46 = function(element)
{
	console.log("frame loaded");
}


showDashboard1 = function()
{
	hideAll();
	showDashboard(0, "button1", "selected1");
}
showDashboard2 = function()
{
	hideAll();
	showDashboard(1, "button2", "selected2");
}

hideAll = function()
{
	deselectButton("button1", "selected1");
	deselectButton("button2", "selected2");
}

deselectButton = function(buttonId, buttonSelected)
{
	var button = $('#' + buttonId);
	if(button && button.hasClass(buttonSelected))
	{
		button.removeClass(buttonSelected);
	}
}

showDashboard = function(dashboardIndex, buttonId, selectedClass)
{
	$("#iframe1")[0].src = dashboards[dashboardIndex];
	$("#" + buttonId).addClass(selectedClass);
}

$("#iframe1").ready(function()
{
	showDashboard1();
});
