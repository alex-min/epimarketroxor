$("form").submit(function (a) {
		var inputs = $("input");
		for (var i = 0; i < inputs.length; ++i) {
			console.log($(inputs[i]));
			if ($(inputs[i]).hasClass('ac_input')) {
				$(inputs[i]).val($(inputs[i]).attr("dbid"));
				//$(inputs[i]).css({color: "#ffffff"});
				alert('');
			}
		}
});
$(document).ready(function () {
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

});
