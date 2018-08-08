var winCal;
var dtToday = new Date();
var Cal;
var docCal;
var MonthName = [ "January", "February", "March", "April", "May", "June",
		"July", "August", "September", "October", "November", "December" ];
var WeekDayName = [ "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday",
		"Friday", "Saturday" ];
var exDateTime;
var cnTop = "200";
var cnLeft = "500";
var WindowTitle = "DateTime Picker";
var WeekChar = 2;
var CellWidth = 20;
var DateSeparator = "-";
var TimeMode = 24;
var ShowLongMonth = true;
var ShowMonthYear = true;
var MonthYearColor = "black";
var WeekHeadColor = "#89a285";
var SundayColor = "#cedacd";
var SaturdayColor = "#cedacd";
var WeekDayColor = "white";
var FontColor = "black";
var TodayColor = "#FFFF33";
var SelDateColor = "#FFFF99";
var YrSelColor = "#89a285";
var ThemeBg = "";
function NewCal(f, j, d, k) {
	Cal = new Calendar(dtToday);
	if ((d != null) && (d)) {
		Cal.ShowTime = true;
		if ((k != null) && ((k == "12") || (k == "24"))) {
			TimeMode = k;
		}
	}
	if (f != null) {
		Cal.Ctrl = f;
	}
	if (j != null) {
		Cal.Format = j.toUpperCase();
	}
	exDateTime = document.getElementById(f).value;
	if (exDateTime != "") {
		var o;
		var n;
		var g;
		var g;
		var e;
		var h;
		var c;
		var m;
		var p;
		var l;
		var b;
		var a;
		o = exDateTime.indexOf(DateSeparator, 0);
		n = exDateTime.indexOf(DateSeparator, (parseInt(o) + 1));
		if ((Cal.Format.toUpperCase() == "DDMMYYYY")
				|| (Cal.Format.toUpperCase() == "DDMMMYYYY")) {
			e = exDateTime.substring(o + 1, n);
			h = exDateTime.substring(0, o);
		} else {
			if ((Cal.Format.toUpperCase() == "MMDDYYYY")
					|| (Cal.Format.toUpperCase() == "MMMDDYYYY")) {
				e = exDateTime.substring(0, o);
				h = exDateTime.substring(o + 1, n);
			}
		}
		if (isNaN(e)) {
			m = Cal.GetMonthIndex(e);
		} else {
			m = parseInt(e, 10) - 1;
		}
		if ((parseInt(m, 10) >= 0) && (parseInt(m, 10) < 12)) {
			Cal.Month = m;
		}
		if ((parseInt(h, 10) <= Cal.GetMonDays()) && (parseInt(h, 10) >= 1)) {
			Cal.Date = h;
		}
		c = exDateTime.substring(n + 1, n + 5);
		p = /^\d{4}$/;
		if (p.test(c)) {
			Cal.Year = parseInt(c, 10);
		}
		if (Cal.ShowTime == true) {
			g = exDateTime.indexOf(":", 0);
			tSp2 = exDateTime.indexOf(":", (parseInt(g) + 1));
			l = exDateTime.substring(g, (g) - 2);
			Cal.SetHour(l);
			b = exDateTime.substring(g + 1, tSp2);
			Cal.SetMinute(b);
			a = exDateTime.substring(tSp2 + 1, tSp2 + 3);
			Cal.SetSecond(a);
		}
	}
	winCal = window
			.open(
					"",
					"DateTimePicker",
					"toolbar=0,status=0,menubar=0,fullscreen=no,width=195,height=245,resizable=0,top="
							+ cnTop + ",left=" + cnLeft);
	docCal = winCal.document;
	RenderCal();
}
function padWithZero(a, b) {
	a = String(a);
	b = parseInt(b) || 2;
	while (a.length < b) {
		a = "0" + a;
	}
	return a;
}
function RenderCal() {
	var g;
	var d;
	var b;
	var l;
	var k;
	var f;
	var m = 0;
	var n;
	docCal.open();
	docCal.writeln("<html><head><title>" + WindowTitle + "</title>");
	docCal.writeln("<script>var winMain=window.opener;<\/script>");
	docCal.writeln("</head><body background='" + ThemeBg + "' link="
			+ FontColor + " vlink=" + FontColor + "><form name='Calendar'>");
	g = '<table border=1 cellpadding=1 cellspacing=1 width=\'100%\' align="center" valign="top">\n';
	g += "<tr>\n<td colspan='7'><table border=0 width='100%' cellpadding=0 cellspacing=0><tr><td align='left'>\n";
	g += '<select name="MonthSelector" onChange="javascript:winMain.Cal.SwitchMth(this.selectedIndex);winMain.RenderCal();">\n';
	for (l = 0; l < 12; l++) {
		if (l == Cal.Month) {
			f = "Selected";
		} else {
			f = "";
		}
		g += "<option " + f + " value >" + MonthName[l] + "\n";
	}
	g += "</select></td>";
	g += '\n<td align=\'right\'><a href="javascript:winMain.Cal.DecYear();winMain.RenderCal()"><b><font color="'
			+ YrSelColor
			+ '"><</font></b></a><font face="Verdana" color="'
			+ YrSelColor
			+ '" size=2><b> '
			+ Cal.Year
			+ ' </b></font><a href="javascript:winMain.Cal.IncYear();winMain.RenderCal()"><b><font color="'
			+ YrSelColor + '">></font></b></a></td></tr></table></td>\n';
	g += "</tr>";
	if (ShowMonthYear) {
		g += "<tr><td colspan='7'><font face='Verdana' size='2' align='center' color='"
				+ MonthYearColor
				+ "'><b>"
				+ Cal.GetMonthName(ShowLongMonth)
				+ " " + Cal.Year + "</b></font></td></tr>\n";
	}
	g += "<tr bgcolor=" + WeekHeadColor + ">";
	for (l = 0; l < 7; l++) {
		g += "<td align='center'><font face='Verdana' size='2'>"
				+ WeekDayName[l].substr(0, WeekChar) + "</font></td>";
	}
	g += "</tr>";
	docCal.write(g);
	CalDate = new Date(Cal.Year, Cal.Month);
	CalDate.setDate(1);
	n = CalDate.getDay();
	d = "<tr>";
	for (l = 0; l < n; l++) {
		d = d + GenCell();
		m = m + 1;
	}
	for (k = 1; k <= Cal.GetMonDays(); k++) {
		var h;
		m = m + 1;
		if ((k == dtToday.getDate()) && (Cal.Month == dtToday.getMonth())
				&& (Cal.Year == dtToday.getFullYear())) {
			h = GenCell(k, true, TodayColor);
		} else {
			if (k == Cal.Date) {
				h = GenCell(k, true, SelDateColor);
			} else {
				if (m % 7 == 0) {
					h = GenCell(k, false, SaturdayColor);
				} else {
					if ((m + 6) % 7 == 0) {
						h = GenCell(k, false, SundayColor);
					} else {
						h = GenCell(k, null, WeekDayColor);
					}
				}
			}
		}
		d = d + h;
		if ((m % 7 == 0) && (k < Cal.GetMonDays())) {
			d = d + "</tr>\n<tr>";
		}
	}
	docCal.writeln(d);
	if (Cal.ShowTime) {
		var c;
		c = Cal.getShowHour();
		b = "<tr>\n<td colspan='7' align='center'>";
		b += "<input type='text' name='hour' maxlength=2 size=1 style=\"WIDTH: 22px\" value="
				+ c + ' onchange="javascript:winMain.Cal.SetHour(this.value)">';
		b += " : ";
		b += "<input type='text' name='minute' maxlength=2 size=1 style=\"WIDTH: 22px\" value="
				+ Cal.Minutes
				+ ' onchange="javascript:winMain.Cal.SetMinute(this.value)">';
		b += " : ";
		b += "<input type='text' name='second' maxlength=2 size=1 style=\"WIDTH: 22px\" value="
				+ Cal.Seconds
				+ ' onchange="javascript:winMain.Cal.SetSecond(this.value)">';
		if (TimeMode == 12) {
			var e = (parseInt(Cal.Hours, 10) < 12) ? "Selected" : "";
			var a = (parseInt(Cal.Hours, 10) >= 12) ? "Selected" : "";
			b += '<select name="ampm" onchange="javascript:winMain.Cal.SetAmPm(this.options[this.selectedIndex].value);">';
			b += "<option " + e + ' value="AM">AM</option>';
			b += "<option " + a + ' value="PM">PM<option>';
			b += "</select>";
		}
		b += "\n</td>\n</tr>";
		docCal.write(b);
	}
	docCal.writeln("\n</table>");
	docCal.writeln("</form></body></html>");
	docCal.close();
}
function GenCell(h, d, f) {
	var a;
	var b;
	var c;
	var g;
	var j;
	var e;
	if (h == null) {
		a = "";
	} else {
		a = h;
	}
	if (f != null) {
		c = 'bgcolor="' + f + '"';
	} else {
		c = "";
	}
	if ((d != null) && (d)) {
		g = "color='red'><b>";
		vHLstr2 = "</b>";
	} else {
		g = ">";
		vHLstr2 = "";
	}
	if (Cal.ShowTime) {
		e = "winMain.document.getElementById('"
				+ Cal.Ctrl
				+ "').value+=' '+winMain.Cal.getShowHour()+':'+winMain.Cal.Minutes+':'+winMain.Cal.Seconds";
		if (TimeMode == 12) {
			e += "+' '+winMain.Cal.AMorPM";
		}
	} else {
		e = "";
	}
	b = "<td " + c + " width=" + CellWidth
			+ " align='center'><font face='verdana' size='2'" + g
			+ "<a href=\"javascript:winMain.document.getElementById('"
			+ Cal.Ctrl + "').value='" + Cal.FormatDate(a) + "';" + e
			+ ';window.close();">' + a + "</a>" + vHLstr2 + "</font></td>";
	return b;
}
function Calendar(a, b) {
	this.Month = a.getMonth();
	this.Date = a.getDate();
	this.Year = a.getFullYear();
	this.Hours = a.getHours();
	if (a.getMinutes() < 10) {
		this.Minutes = "0" + a.getMinutes();
	} else {
		this.Minutes = a.getMinutes();
	}
	if (a.getSeconds() < 10) {
		this.Seconds = "0" + a.getSeconds();
	} else {
		this.Seconds = a.getSeconds();
	}
	this.MyWindow = winCal;
	this.Ctrl = b;
	this.Format = "MMddyyyy";
	this.Separator = DateSeparator;
	this.ShowTime = false;
	if (a.getHours() < 12) {
		this.AMorPM = "AM";
	} else {
		this.AMorPM = "PM";
	}
}
function GetMonthIndex(a) {
	for (i = 0; i < 12; i++) {
		if (MonthName[i].substring(0, 3).toUpperCase() == a.toUpperCase()) {
			return i;
		}
	}
	return true;
}
Calendar.prototype.GetMonthIndex = GetMonthIndex;
function IncYear() {
	Cal.Year++;
}
Calendar.prototype.IncYear = IncYear;
function DecYear() {
	Cal.Year--;
}
Calendar.prototype.DecYear = DecYear;
function SwitchMth(a) {
	Cal.Month = a;
}
Calendar.prototype.SwitchMth = SwitchMth;
function SetHour(a) {
	var c;
	var b;
	if (TimeMode == 24) {
		c = 23;
		b = 0;
	} else {
		if (TimeMode == 12) {
			c = 12;
			b = 1;
		} else {
			alert("TimeMode can only be 12 or 24");
		}
	}
	var d = new RegExp("^\\d\\d$");
	if (d.test(a) && (parseInt(a, 10) <= c) && (parseInt(a, 10) >= b)) {
		if ((TimeMode == 12) && (Cal.AMorPM == "PM")) {
			if (parseInt(a, 10) == 12) {
				Cal.Hours = 12;
			} else {
				Cal.Hours = parseInt(a, 10) + 12;
			}
		} else {
			if ((TimeMode == 12) && (Cal.AMorPM == "AM")) {
				if (a == 12) {
					a -= 12;
				}
				Cal.Hours = parseInt(a, 10);
			} else {
				if (TimeMode == 24) {
					Cal.Hours = parseInt(a, 10);
				}
			}
		}
	}
}
Calendar.prototype.SetHour = SetHour;
function SetMinute(a) {
	var b = new RegExp("^\\d\\d$");
	if (b.test(a) && (a < 60)) {
		Cal.Minutes = a;
	}
}
Calendar.prototype.SetMinute = SetMinute;
function SetSecond(a) {
	var b = new RegExp("^\\d\\d$");
	if (b.test(a) && (a < 60)) {
		Cal.Seconds = a;
	}
}
Calendar.prototype.SetSecond = SetSecond;
function SetAmPm(a) {
	this.AMorPM = a;
	if (a == "PM") {
		this.Hours = (parseInt(this.Hours, 10)) + 12;
		if (this.Hours == 24) {
			this.Hours = 12;
		}
	} else {
		if (a == "AM") {
			this.Hours -= 12;
		}
	}
}
Calendar.prototype.SetAmPm = SetAmPm;
function getShowHour() {
	var a;
	if (TimeMode == 12) {
		if (parseInt(this.Hours, 10) == 0) {
			this.AMorPM = "AM";
			a = parseInt(this.Hours, 10) + 12;
		} else {
			if (parseInt(this.Hours, 10) == 12) {
				this.AMorPM = "PM";
				a = 12;
			} else {
				if (this.Hours > 12) {
					this.AMorPM = "PM";
					if ((this.Hours - 12) < 10) {
						a = "0" + ((parseInt(this.Hours, 10)) - 12);
					} else {
						a = parseInt(this.Hours, 10) - 12;
					}
				} else {
					this.AMorPM = "AM";
					if (this.Hours < 10) {
						a = "0" + parseInt(this.Hours, 10);
					} else {
						a = this.Hours;
					}
				}
			}
		}
	} else {
		if (TimeMode == 24) {
			if (this.Hours < 10) {
				a = "0" + parseInt(this.Hours, 10);
			} else {
				a = this.Hours;
			}
		}
	}
	return a;
}
Calendar.prototype.getShowHour = getShowHour;
function GetMonthName(b) {
	var a = MonthName[this.Month];
	if (b) {
		return a;
	} else {
		return a.substr(0, 3);
	}
}
Calendar.prototype.GetMonthName = GetMonthName;
function GetMonDays() {
	var a = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];
	if (this.IsLeapYear()) {
		a[1] = 29;
	}
	return a[this.Month];
}
Calendar.prototype.GetMonDays = GetMonDays;
function IsLeapYear() {
	if ((this.Year % 4) == 0) {
		if ((this.Year % 100 == 0) && (this.Year % 400) != 0) {
			return false;
		} else {
			return true;
		}
	} else {
		return false;
	}
}
Calendar.prototype.IsLeapYear = IsLeapYear;
function FormatDate(a) {
	if (this.Format.toUpperCase() == "DDMMYYYY") {
		return (padWithZero(a, 2) + DateSeparator
				+ padWithZero((this.Month + 1), 2) + DateSeparator + this.Year);
	} else {
		if (this.Format.toUpperCase() == "DDMMMYYYY") {
			return (padWithZero(a, 2) + DateSeparator
					+ this.GetMonthName(false) + DateSeparator + this.Year);
		} else {
			if (this.Format.toUpperCase() == "MMDDYYYY") {
				return (padWithZero((this.Month + 1), 2) + DateSeparator
						+ padWithZero(a, 2) + DateSeparator + this.Year);
			} else {
				if (this.Format.toUpperCase() == "MMMDDYYYY") {
					return (this.GetMonthName(false) + DateSeparator
							+ padWithZero(a, 2) + DateSeparator + this.Year);
				}
			}
		}
	}
	return true;
}
Calendar.prototype.FormatDate = FormatDate;