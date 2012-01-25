$("form").submit(function (a) {
		var inputs = $(".post");
		for (var i = 0; i < inputs.length; ++i) {
			if ($(inputs[i]).hasClass('ac_input')) {
				$(inputs[i]).val($(inputs[i]).attr("dbid"));
				//$(inputs[i]).css({color: "#ffffff"});
				alert('');
			}
		}
});

function postThis( obj ) {
	if (obj == undefined || obj.length == 0) return ;
	$(".onselect").each(function () {$(this).removeClass("onselect");});
	obj.removeClass("onselect");
	obj.html($("#shadowtext").val());
	obj.css({"visibility" : "visible"});
	var parentTd = obj.parent("td");
	var tdPlace = parentTd.parent().children().index(parentTd[0]);
	var thPlace = $(obj.parents("table").children()[0]).find("th")[tdPlace];
	var tableName = $(obj).parents("table").attr("abbr");
	var fieldName = $(thPlace).attr("abbr");
	var id = $($(obj.parents("tr")).children()[0]).children()[0].innerHTML;
	$("#shadowtext").css({"visibility" : "hidden"});
	$.post("/epimarket/app/ajax/change/" + tableName + "/"
			+ fieldName + "/" +
			id
			,
			
			"data=" + encodeURI(obj.html())
	);
	if (obj.hasClass("replacenext")) {
		$(obj.parents("td").next().children()[0]).html(obj.html());
		autoreplaceall();
	}
	
}

var autoreplaceall = undefined;
var replacecontent = undefined;
$(document).ready(function () {
	$(".editable").click(function () {
			if ($("#shadowinput").length == 0) {
				$('body').append('<div id="shadowinput"> \
						<input id="shadowtext"/>\
						</div>');
			} else {
				$(".onselect").each(function () {
					postThis($(this));
				});
			}
			$("#shadowtext").val($(this).html());
			$(this).css({"visibility" : "hidden"});
			$(this).addClass("onselect");
			$("#shadowtext").css({
				"visibility" : "visible",
				"position" : "absolute",
				"top" : $(this).position().top + "px",
				"left" : $(this).position().left + "px",
				"width" : $(this).width(),
				"height" : $(this).height()
			});
			$("#shadowtext").keypress(function (event) {
				if (event.which == 13) {
					postThis($($(".onselect")[0]));
				}
			});
			
		}
	);
	
	
	function parseData(data) {
		if (!data) return null;
		var parsed = [];
		var rows = data.split("\n");
		for (var i=0; i < rows.length; i++) {
			var row = $.trim(rows[i]);
			if (row) {
				parsed[parsed.length] = row.split("|");
			}
		}
		return parsed;
	};
$(".ac_input").val("");
var ar = [];
var replaceType = undefined;
function replaceContent(obj) {
for (var i = 0; i < ar[replaceType].length; ++i) {
	if (parseInt(ar[replaceType][i][1]) == parseInt(obj.innerHTML)) {
		obj.innerHTML = ar[replaceType][i][0];
	}
}
}
replacecontent == replaceContent;
function autoreplace() {
$(".autoreplace").each(function () {
	var obj = this;
	replaceType = $(this).attr("abbr");
	if (ar[replaceType] === undefined) {
		$.ajax({ 
				'async' : false,
				'url' : "/epimarket/app/ajax/" + $(this).attr("abbr") + "/auto",
				'success' : function (data) {
				ar[replaceType] = parseData(data);
				replaceContent(obj);
				}
		}
		); 
	} else {
		replaceContent(obj);
	}
	
});
} autoreplaceall = autoreplace;
autoreplaceall();
});

