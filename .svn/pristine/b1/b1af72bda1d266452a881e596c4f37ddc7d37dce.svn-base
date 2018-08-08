function padWithZero(a, b) {
	a = String(a);
	b = parseInt(b) || 2;
	while (a.length < b) {
		a = "0" + a;
	}
	return a;
}
var timeDiff = {
	setStartTime : function() {
		d = new Date();
		time = d.getTime();
	},
	getDiff : function() {
		d = new Date();
		return ((d.getTime() - time) / 1000) + " seconds";
	}
};
function sortFuncAsc(e, b) {
	var c = e.text.toLowerCase();
	var a = b.text.toLowerCase();
	if (c > a) {
		return (1);
	}
	if (c < a) {
		return (-1);
	}
	return (0);
}
function isValidEmail(a) {
	var b = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i
			.test(a);
	return b;
}
jQuery.fn.sortOptions = function() {
	return this.each(function() {
		var a = jQuery(this);
		a.html($("option", a).sort(sortFuncAsc));
	});
};
jQuery.query = function(a) {
	var b = {};
	if (a) {
		var c = a.substring(a.indexOf("?") + 1);
		c = c.replace(/\&$/, "");
		jQuery.each(c.split("&"), function() {
			var g = this.split("=");
			var e = g[0];
			var f = g[1];
			if (/^[0-9.]+$/.test(f)) {
				f = parseFloat(f);
			}
			if (f == "true") {
				f = true;
			}
			if (f == "false") {
				f = false;
			}
			if (typeof f == "number" || typeof f == "boolean" || f.length > 0) {
				b[e] = f;
			}
		});
	}
	return b;
};
function unselectAll(a) {
	$("#" + a + " option").each(function(b) {
		$(this).attr("selected", "");
	});
}
function selectAll(a) {
	$("#" + a + " option").each(function(b) {
		$(this).attr("selected", "selected");
	});
}
jQuery.fn.preventDoubleSubmit = function() {
	jQuery(this).submit(function() {
		if (this.beenSubmitted) {
			return false;
		} else {
			this.beenSubmitted = true;
		}
	});
};
$().ready(
		function() {
			$("textarea[maxlength]").keyup(
					function(b) {
						var a = $(this).attr("maxlength");
						var c = $(this).val().length;
						if (c > a) {
							$(this).val($(this).val().substring(0, a));
						}
						$("+ .remainingChars", this).html(
								(a - c) + " characters remaining");
					});
			$("textarea[maxlength]").keypress(function(c) {
				var b = c.which;
				var a = $(this).attr("maxlength");
				var e = $(this).val().length;
				if (b != 8 && b != 0 && e == a) {
					c.preventDefault();
				}
			});
			$("textarea[maxlength]").each(
					function() {
						$(this).after(
								"<div class='remainingChars'>"
										+ ($(this).attr("maxlength") - $(this)
												.val().length)
										+ " characters remaining</div>");
					});
			$("form").each(function() {
				$(this).preventDoubleSubmit();
			});
		});

function showSubInfo(subTabId,newImg) {
	$("#" + subTabId).removeClass("hideMe");
	$("#" + subTabId + "ExpImg").attr("src", newImg);
	$("#" + subTabId + "NewWorklist").removeClass("hideMe");
}
function hideSubInfo(subTabId,newImg) {
	$("#" + subTabId).addClass("hideMe");
	$("#" + subTabId + "ExpImg").attr("src",newImg);
	$("#" + subTabId + "NewWorklist").addClass("hideMe");
}
function addClass(ele, cls) {
	if (!this.hasClass(ele, cls))
		ele.className += " " + cls;
}
function removeClass(ele, cls) {
	if (ele != null && hasClass(ele, cls)) {
		var reg = new RegExp('(\\s|^)' + cls + '(\\s|$)');
		ele.className = ele.className.replace(reg, ' ');
	}
}
function hasClass(ele, cls) {
	return ele.className.match(new RegExp('(\\s|^)' + cls + '(\\s|$)'));
}
function showThisRow(tableId, thisRowId) {
	var table = document.getElementById(tableId);
	var rows = table.getElementsByTagName("tr");
	for (i = 0; i < rows.length; i++) {
		if (rows[i].id == thisRowId) {
			removeClass(rows[i], "hideMe");
			break;
		}
	}
}
function hideThisRow(tableId, thisRowId) {
	var table = document.getElementById(tableId);
	var rows = table.getElementsByTagName("tr");
	for (i = 0; i < rows.length; i++) {
		if (rows[i].id == thisRowId) {
			addClass(rows[i], "hideMe");
			break;
		}
	}
}