package com.trescantos.link1;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecFactory;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultRedirectHandler;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.os.AsyncTask;

class RetreiveFeedTask extends AsyncTask<String, Void, String> {

	private Exception exception;
	
	String head="<head>\n" +
                "\n" +
                "  <base href=\"http://www.trescantos.es/web/component/k2/itemlist\" />\n" +
                "  <meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />\n" +
                "  <meta property=\"og:url\" content=\"http://www.trescantos.es/web/component/k2/itemlist\" />\n" +
                "  <meta property=\"og:title\" content=\"Noticias Tres Cantos\" />\n" +
                "  <meta property=\"og:type\" content=\"website\" />\n" +
                "\n" +
                "  <title>Noticias Tres Cantos</title>\n" +
                "\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html;\" />\n" +
                "<meta http-equiv=\"Content-Style-Type\" content=\"text/css\" />\n" +
                "\n" +
                "<meta name=\"viewport\" content=\"initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no\" />\n" +
                "\n" +
                "<style type=\"text/css\"> \n" +
                "\n" +
                "\n" +
                "\n" +
                "/* MAX IMAGE WIDTH */\n" +
                "\n" +
                "img {\n" +
                "height:auto !important;\n" +
                "max-width:100% !important;\n" +
                "-webkit-box-sizing: border-box !important; /* Safari/Chrome, other WebKit */\n" +
                "-moz-box-sizing: border-box !important;    /* Firefox, other Gecko */\n" +
                "box-sizing: border-box !important;         /* Opera/IE 8+ */\n" +
                "}\n" +
                "\n" +
                ".full_width {\n" +
                "width:100% !important;\n" +
                "-webkit-box-sizing: border-box !important; /* Safari/Chrome, other WebKit */\n" +
                "-moz-box-sizing: border-box !important;    /* Firefox, other Gecko */\n" +
                "box-sizing: border-box !important;         /* Opera/IE 8+ */\n" +
                "}\n" +
                "\n" +
                "\n" +
                "\n" +
                "#s5_responsive_modile_drop_down_wrap input {\n" +
                "width:96% !important;\n" +
                "}\n" +
                "#s5_responsive_mobile_drop_down_search input {\n" +
                "width:100% !important;\n" +
                "}\n" +
                "\n" +
                "\n" +
                "\n" +
                "@media screen and (max-width: 750px){\n" +
                "\tbody {\n" +
                "\theight:100% !important;\n" +
                "\tposition:relative !important;\n" +
                "\tpadding-bottom:48px !important;\n" +
                "\t}\n" +
                "}\n" +
                "\n" +
                "\n" +
                "\t#s5_responsive_mobile_bottom_bar, #s5_responsive_mobile_top_bar {\n" +
                "\tbackground:#0B0B0B;\n" +
                "\tbackground: -moz-linear-gradient(top, #272727 0%, #0B0B0B 100%); /* FF3.6+ */\n" +
                "\tbackground: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#272727), color-stop(100%,#0B0B0B)); /* Chrome,Safari4+ */\n" +
                "\tbackground: -webkit-linear-gradient(top, #272727 0%,#0B0B0B 100%); /* Chrome10+,Safari5.1+ */\n" +
                "\tbackground: -o-linear-gradient(top, #272727 0%,#0B0B0B 100%); /* Opera11.10+ */\n" +
                "\tbackground: -ms-linear-gradient(top, #272727 0%,#0B0B0B 100%); /* IE10+ */\n" +
                "\tbackground: linear-gradient(top, #272727 0%,#0B0B0B 100%); /* W3C */\n" +
                "\t\t\tfont-family: Oswald !important;\n" +
                "\t}\n" +
                "\t\n" +
                "\t.s5_responsive_mobile_drop_down_inner, .s5_responsive_mobile_drop_down_inner input, .s5_responsive_mobile_drop_down_inner button, .s5_responsive_mobile_drop_down_inner .button, #s5_responsive_mobile_drop_down_search .validate {\n" +
                "\tfont-family: Oswald !important;\n" +
                "\t}\n" +
                "\t\n" +
                "\t.s5_responsive_mobile_drop_down_inner button:hover, .s5_responsive_mobile_drop_down_inner .button:hover {\n" +
                "\tbackground:#0B0B0B !important;\n" +
                "\t}\n" +
                "\t\n" +
                "\t#s5_responsive_mobile_drop_down_menu, #s5_responsive_mobile_drop_down_menu a, #s5_responsive_mobile_drop_down_login a {\n" +
                "\tfont-family: Oswald !important;\n" +
                "\tcolor:#FFFFFF !important;\n" +
                "\t}\n" +
                "\t\n" +
                "\t#s5_responsive_mobile_bar_active, #s5_responsive_mobile_drop_down_menu .current a, .s5_responsive_mobile_drop_down_inner .s5_mod_h3, .s5_responsive_mobile_drop_down_inner .s5_h3_first {\n" +
                "\tcolor:#73A0CF !important;\n" +
                "\t}\n" +
                "\t\n" +
                "\t.s5_responsive_mobile_drop_down_inner button, .s5_responsive_mobile_drop_down_inner .button {\n" +
                "\tbackground:#73A0CF !important;\n" +
                "\t}\n" +
                "\t\n" +
                "\t#s5_responsive_mobile_drop_down_menu .active ul li, #s5_responsive_mobile_drop_down_menu .current ul li a, #s5_responsive_switch_mobile a, #s5_responsive_switch_desktop a, #s5_responsive_modile_drop_down_wrap {\n" +
                "\tcolor:#FFFFFF !important;\n" +
                "\t}\n" +
                "\t\n" +
                "\t#s5_responsive_mobile_toggle_click_menu span {\n" +
                "\tborder-right:solid 1px #272727;\n" +
                "\t}\n" +
                "\n" +
                "\t#s5_responsive_mobile_toggle_click_menu {\n" +
                "\tborder-right:solid 1px #0B0B0B;\n" +
                "\t}\n" +
                "\n" +
                "\t#s5_responsive_mobile_toggle_click_search span, #s5_responsive_mobile_toggle_click_register span, #s5_responsive_mobile_toggle_click_login span, #s5_responsive_mobile_scroll a {\n" +
                "\tborder-left:solid 1px #272727;\n" +
                "\t}\n" +
                "\n" +
                "\t#s5_responsive_mobile_toggle_click_search, #s5_responsive_mobile_toggle_click_register, #s5_responsive_mobile_toggle_click_login, #s5_responsive_mobile_scroll {\n" +
                "\tborder-left:solid 1px #0B0B0B;\n" +
                "\t}\n" +
                "\n" +
                "\t.s5_responsive_mobile_open, .s5_responsive_mobile_closed:hover, #s5_responsive_mobile_scroll:hover {\n" +
                "\tbackground:#272727;\n" +
                "\t}\n" +
                "\n" +
                "\t#s5_responsive_mobile_drop_down_menu .s5_responsive_mobile_drop_down_inner, #s5_responsive_mobile_drop_down_register .s5_responsive_mobile_drop_down_inner, #s5_responsive_mobile_drop_down_login .s5_responsive_mobile_drop_down_inner, #s5_responsive_mobile_drop_down_search .s5_responsive_mobile_drop_down_inner {\n" +
                "\tbackground:#272727;\n" +
                "\t}\n" +
                "\n" +
                "\t.s5_wrap {\n" +
                "\tmax-width:960px !important;\n" +
                "\t}\n" +
                "\n" +
                "\t@media screen and (max-width: 970px){\n" +
                "\t\n" +
                "\t\t#s5_right_top_wrap {\n" +
                "\t\twidth:0px !important;\n" +
                "\t\t}\n" +
                "\t\t#s5_right_inset_wrap {\n" +
                "\t\twidth:0px !important;\n" +
                "\t\t}\n" +
                "\t\t#s5_right_wrap {\n" +
                "\t\twidth:0px !important;\n" +
                "\t\t}\n" +
                "\t\t#s5_right_bottom_wrap {\n" +
                "\t\twidth:0px !important;\n" +
                "\t\t}\n" +
                "\t\t#s5_left_top_wrap {\n" +
                "\t\twidth:0px !important;\n" +
                "\t\t}\n" +
                "\t\t#s5_left_inset_wrap {\n" +
                "\t\twidth:0px !important;\n" +
                "\t\t}\n" +
                "\t\t#s5_left_wrap {\n" +
                "\t\twidth:0px !important;\n" +
                "\t\t}\n" +
                "\t\t#s5_left_bottom_wrap {\n" +
                "\t\twidth:0px !important;\n" +
                "\t\t}\n" +
                "\t\t#s5_right_column_wrap {\n" +
                "\t\twidth:0px !important;\n" +
                "\t\tmargin-left:-0px !important;\n" +
                "\t\t}\n" +
                "\t\t#s5_left_column_wrap {\n" +
                "\t\twidth:0px !important;\n" +
                "\t\t}\n" +
                "\t\t#s5_center_column_wrap_inner {\n" +
                "\t\tmargin-left:0px !important;\n" +
                "\t\tmargin-right:0px !important;\n" +
                "\t\t}\n" +
                "\t\n" +
                "\t}\n" +
                "\n" +
                "</style>\n" +
                "\t\n" +
                "<link rel=\"stylesheet\" href=\"http://www.trescantos.es/web/templates/system/css/system.css\" type=\"text/css\" />\n" +
                "<link rel=\"stylesheet\" href=\"http://www.trescantos.es/web/templates/system/css/general.css\" type=\"text/css\" />\n" +
                "\n" +
                "<link href=\"http://www.trescantos.es/web/templates/shape5_vertex/css/template_default.css\" rel=\"stylesheet\" type=\"text/css\" />\n" +
                "<link href=\"http://www.trescantos.es/web/templates/shape5_vertex/css/template.css\" rel=\"stylesheet\" type=\"text/css\" />\n" +
                "<link href=\"http://www.trescantos.es/web/templates/shape5_vertex/css/com_content.css\" rel=\"stylesheet\" type=\"text/css\" />\n" +
                "<link href=\"http://www.trescantos.es/web/templates/shape5_vertex/css/editor.css\" rel=\"stylesheet\" type=\"text/css\" />\n" +
                "<link href=\"http://www.trescantos.es/web/templates/shape5_vertex/css/thirdparty.css\" rel=\"stylesheet\" type=\"text/css\" />\n" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"http://fonts.googleapis.com/css?family=Open Sans\" />\n" +
                "<link href=\"http://www.trescantos.es/web/templates/shape5_vertex/favicon.ico\" rel=\"shortcut icon\" type=\"image/x-icon\" />\n" +
                "\t<link rel=\"stylesheet\" type=\"text/css\" href=\"http://www.trescantos.es/web/templates/shape5_vertex/css/s5_responsive_bars.css\" />\n" +
                "\t<link href=\"http://www.trescantos.es/web/templates/shape5_vertex/css/s5_responsive_hide_classes.css\" rel=\"stylesheet\" type=\"text/css\" />\n" +
                "\t<link rel=\"stylesheet\" type=\"text/css\" href=\"http://www.trescantos.es/web/templates/shape5_vertex/css/s5_responsive.css\" />\n" +
                "\t\n" +
                "\t\n" +
                "\n" +
                "<!-- Info Slide Script - Called in header so css validates -->\t\n" +
                "\n" +
                "\n" +
                "<!-- File compression. Needs to be called last on this file -->\t\n" +
                "<!-- The excluded files, listed below the compressed php files, are excluded because their calls vary per device or per browser. Included compression files are only ones that have no conditions and are included on all devices and browsers. Otherwise unwanted css will compile in the compressed files. -->\t\n" +
                "\n" +
                "\n" +
                "\n" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"http://fonts.googleapis.com/css?family=Open Sans\" />\n" +
                "\n" +
                "<style type=\"text/css\"> \n" +
                "body {font-family: 'Open Sans',Helvetica,Arial,Sans-Serif ;\n" +
                "background:#215098;\n" +
                "} \n" +
                "\n" +
                "#s5_search input, #s5_menu_wrap, .s5_mod_h3, #subMenusContainer, h2 {\n" +
                "font-family: Open Sans;\n" +
                "}\n" +
                "\n" +
                "#s5_menu_outer_wrap {\n" +
                "height:40px;\n" +
                "overflow:hidden;\n" +
                "}\n" +
                ".S5_parent_subtext {\n" +
                "display:none;\n" +
                "}\n" +
                "#s5_nav li {\n" +
                "height:37px;\n" +
                "}\n" +
                "#s5_search input {\n" +
                "margin-top:0px;\n" +
                "}\n" +
                "\n" +
                "#s5_header_area_inner2, .module_round_box, .module_round_box-dark, #s5_component_wrap, #s5_footer_area_inner2 {\n" +
                "-webkit-box-shadow: 0 0px 8px #ffffff;\n" +
                "-moz-box-shadow: 0 0px 8px #ffffff;\n" +
                "box-shadow: 0 0px 8px #ffffff; \n" +
                "}\n" +
                "\n" +
                "a, .module_round_box .s5_h3_first, .module_round_box-none .s5_h3_first, .module_round_box ul.menu .current a, h2, h4, #s5_md_outer_wrap h3 {\n" +
                "color:#1865b4;\n" +
                "}\n" +
                "\n" +
                "#s5_nav li.active a, #s5_nav li.mainMenuParentBtnFocused a, #s5_nav li:hover a {\n" +
                "color:#1865b4;\n" +
                "}\n" +
                "\n" +
                ".button, li.pagenav-next, li.pagenav-prev, .validate {\n" +
                "background:#1865b4;\n" +
                "}\n" +
                "\n" +
                "#subMenusContainer div.s5_sub_wrap ul, #subMenusContainer div.s5_sub_wrap_rtl ul, #subMenusContainer div.s5_sub_wrap_lower ul, #subMenusContainer div.s5_sub_wrap_lower_rtl ul {\n" +
                "border-bottom:solid 3px #1865b4;\n" +
                "}\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "/* k2 stuff */\n" +
                "div.itemHeader h2.itemTitle, div.catItemHeader h3.catItemTitle, h3.userItemTitle a, #comments-form p, #comments-report-form p, #comments-form span, #comments-form .counter, #comments .comment-author, #comments .author-homepage,\n" +
                "#comments-form p, #comments-form #comments-form-buttons, #comments-form #comments-form-error, #comments-form #comments-form-captcha-holder {font-family: 'Open Sans',Helvetica,Arial,Sans-Serif ;} \n" +
                "\t\n" +
                ".s5_wrap{width:94%;}\t\n" +
                "</style>\n" +
                "</head>";

	protected String doInBackground(String... urls) {
		String htmlcode = "";
		Document doc;
		try {
//			HttpHost proxy = new HttpHost("proxyj.mju.es", 8080, "http");
			DefaultHttpClient httpclient = new DefaultHttpClient();

			// Create a local instance of cookie store
			CookieStore cookieStore = new BasicCookieStore();

			// Bind custom cookie store to the local context
			httpclient.setCookieStore(cookieStore);
			CookieSpecFactory csf = new CookieSpecFactory() {
				public CookieSpec newInstance(HttpParams params) {
					return new BrowserCompatSpec() {
						@Override
						public void validate(Cookie cookie, CookieOrigin origin)
								throws MalformedCookieException {
							// Oh, I am easy
							// allow all cookies
						}
					};
				}
			};
			httpclient.getCookieSpecs().register("easy", csf);
			httpclient.getParams().setParameter(ClientPNames.COOKIE_POLICY,
					"easy");

			httpclient.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS,
					Boolean.TRUE);
//			httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
//					proxy);
			httpclient.setRedirectHandler(new DefaultRedirectHandler());

			HttpGet html = new HttpGet(
					urls[0]);
			HttpResponse response = httpclient.execute(html);
			String responseBody = EntityUtils.toString(response.getEntity());
			doc = Jsoup.parse(responseBody);
			doc.body().getElementById("s5_header_area1").empty();
			doc.body().getElementById("s5_responsive_mobile_top_bar_wrap").empty();
			
			doc.body().getElementById("s5_bottom_row1_area1").empty();
			doc.body().getElementById("s5_footer_area1").empty();
			doc.body().getElementById("s5_responsive_mobile_bottom_bar_outer").empty();
			
			
			
			doc.body().getElementsByClass("catItemRatingBlock").empty();
			doc.body().getElementsByClass("catItemLinks").empty();
			doc.body().getElementsByClass("catItemReadMore").empty();
			
			
			doc.body().getElementsByAttribute("href").attr("href", "#");
//			Elements elementsByAttribute = doc.body().getElementsByAttribute("href");
//			
//			for(int i=0;i<elementsByAttribute.size();i++)
//			{
//				elementsByAttribute.get(i).attr("href", "#");
//			}

			
			
			
			doc.head().getElementsByTag("head").empty();
			doc.head().getElementsByTag("head").append(head);
//			 Element headElem = doc.createElement("head");
//			 headElem.appendText(head);
//			doc.body().getElementsByTag("html").add(headElem );
//			doc.head().getAllElements().get(0).append(head);
//			doc.head().getElementsByTag("head").append(head);
			htmlcode = doc.html();
			System.out.println(htmlcode);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return htmlcode;
	}

	protected void onPostExecute(String feed) {
		// TODO: check this.exception
		// TODO: do something with the feed
	}
}