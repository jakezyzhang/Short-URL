layui.use('element', function() {
	var element = layui.element;
});
layui.use(['jquery', 'form'], function() {
	var form = layui.form;
	var $ = layui.jquery;
	form.verify({
		tag: function(value, item) {
			if (!new RegExp("^[a-zA-Z0-9]{4,16}").test(value)) {
				return '只能设置为4~16个字符的数字、字母';
			}
		}
	});
	form.on('submit(submit)', function(data) {
		// alert(data.field.url)
		$.ajax({
			// url: 'https://www.zyaiyy.cn/s/getshorturl',
			url:'http://localhost:8083/shorturl/s/getshorturl',
			type: "POST",
			data: {
				"url": data.field.url
			},
			success: function(data) {
				// $("#url").val("http://www.zyaiyy.cn/s/" + data.shortUrl);
				$("#url").val("http://localhost:8083/shorturl/s/" + data.shortUrl);
			},
		});
		return false;
	});
	form.on('submit(appointsubmit)', function(data) {
		// alert(data.field.url)
		$.ajax({
			// url: 'https://www.zyaiyy.cn/s/appointshorturl',
			url:'http://localhost:8083/shorturl/s/appointshorturl',
			type: "POST",
			data: {
				"url": data.field.appointurl,
				"shortUrl": data.field.shortUrl
			},
			success: function(data) {
				if (data.result == "existence") {
					$("#appointurl").val("该短链接已经存在了请更改短链接")
				} else {
					// $("#appointurl").val("https://www.zyaiyy.cn/s/" + data.shortUrl);
					$("#appointurl").val("http://localhost:8083/shorturl/s/" + data.shortUrl);
				}
			},
		});
		return false;
	});
	form.on('submit(countsubmit)', function(data) {
		// alert(data.field.url)
		$.ajax({
			// url: 'https://www.zyaiyy.cn/shorturl/s/getcount',
			url:'http://localhost:8083/shorturl/s/getcount',
			type: "POST",
			data: {
				"shortUrl": data.field.countShortUrl
			},
			success: function(data) {
				// alert(data.result)
				if (data.result == "未生成此短链接") {
					$("#count").val("未生成此短链接");
				} else {
					$("#count").val(data.result.count);
				}

			},
		});
		return false;
	});
});
