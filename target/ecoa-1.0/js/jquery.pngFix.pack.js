eval(function(h, b, i, d, g, f) {
	g = function(a) {
		return (a < 62 ? "" : g(parseInt(a / 62)))
				+ ((a = a % 62) > 35 ? String.fromCharCode(a + 29) : a
						.toString(36));
	};
	if ("0".replace(0, g) == 0) {
		while (i--) {
			f[g(i)] = d[i];
		}
		d = [ function(a) {
			return f[a] || a;
		} ];
		g = function() {
			return "([237-9n-zA-Z]|1\\w)";
		};
		i = 1;
	}
	while (i--) {
		if (d[i]) {
			h = h.replace(new RegExp("\\b" + g(i) + "\\b", "g"), d[i]);
		}
	}
	return h;
}
		(
				"(s(m){3.fn.pngFix=s(c){c=3.extend({P:'blank.gif'},c);8 e=(o.Q==\"t R S\"&&T(o.u)==4&&o.u.A(\"U 5.5\")!=-1);8 f=(o.Q==\"t R S\"&&T(o.u)==4&&o.u.A(\"U 6.0\")!=-1);p(3.browser.msie&&(e||f)){3(2).B(\"img[n$=.C]\").D(s(){3(2).7('q',3(2).q());3(2).7('r',3(2).r());8 a='';8 b='';8 g=(3(2).7('E'))?'E=\"'+3(2).7('E')+'\" ':'';8 h=(3(2).7('F'))?'F=\"'+3(2).7('F')+'\" ':'';8 i=(3(2).7('G'))?'G=\"'+3(2).7('G')+'\" ':'';8 j=(3(2).7('H'))?'H=\"'+3(2).7('H')+'\" ':'';8 k=(3(2).7('V'))?'float:'+3(2).7('V')+';':'';8 d=(3(2).parent().7('href'))?'cursor:hand;':'';p(2.9.v){a+='v:'+2.9.v+';';2.9.v=''}p(2.9.w){a+='w:'+2.9.w+';';2.9.w=''}p(2.9.x){a+='x:'+2.9.x+';';2.9.x=''}8 l=(2.9.cssText);b+='<y '+g+h+i+j;b+='9=\"W:X;white-space:pre-line;Y:Z-10;I:transparent;'+k+d;b+='q:'+3(2).q()+'z;r:'+3(2).r()+'z;';b+='J:K:L.t.M(n=\\''+3(2).7('n')+'\\', N=\\'O\\');';b+=l+'\"></y>';p(a!=''){b='<y 9=\"W:X;Y:Z-10;'+a+d+'q:'+3(2).q()+'z;r:'+3(2).r()+'z;\">'+b+'</y>'}3(2).hide();3(2).after(b)});3(2).B(\"*\").D(s(){8 a=3(2).11('I-12');p(a.A(\".C\")!=-1){8 b=a.13('url(\"')[1].13('\")')[0];3(2).11('I-12','none');3(2).14(0).15.J=\"K:L.t.M(n='\"+b+\"',N='O')\"}});3(2).B(\"input[n$=.C]\").D(s(){8 a=3(2).7('n');3(2).14(0).15.J='K:L.t.M(n=\\''+a+'\\', N=\\'O\\');';3(2).7('n',c.P)})}return 3}})(3);",
				[],
				68,
				"||this|jQuery||||attr|var|style||||||||||||||src|navigator|if|width|height|function|Microsoft|appVersion|border|padding|margin|span|px|indexOf|find|png|each|id|class|title|alt|background|filter|progid|DXImageTransform|AlphaImageLoader|sizingMethod|scale|blankgif|appName|Internet|Explorer|parseInt|MSIE|align|position|relative|display|inline|block|css|image|split|get|runtimeStyle"
						.split("|"), 0, {}));
$().ready(function() {
	$(document).pngFix();
});