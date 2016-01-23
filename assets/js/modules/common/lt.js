(function(window) {
	var w = window;
	var mj = (function() {
		var mj = function(selector, rootmj) {
			return mj.fn.init(selector, rootmj);
		}, rootmj;

		mj.fn = mj.prototype = {
			constructor : mj,
			init : function(selector, rootmj) {
				if (selector != null) {
					this.selector = selector;
				}
				return this;
			},
			mj : "1.0",
			selector : "",
			size : function() {
				return this.length;
			},
			each : function() {
				return mj.each(callback, args);
			}
		};
		mj.fn.init.protorype = mj.fn;

		mj.extend = mj.fn.extend = function() {
			var options, src, copy, target = arguments[0] || {}, i = 1, length = arguments.length;

			// 只有一个参数，就是对jQuery自身的扩展处理
			// extend,fn.extend
			if (i === length) {
				target = this; // 调用的上下文对象mj/或者实例
				i--;
			}
			for (; i < length; i++) {
				// 从i开始取参数,不为空开始遍历
				if ((options = arguments[i]) != null) {
					for (name in options) {
						copy = options[name];
						// 覆盖拷贝
						target[name] = copy;
					}
				}
			}
			return target;
		}

		rootmj = mj(document);

		/**
		 * 判断值是否为空，如果为空就返回true<br>
		 * 不可用于判断dom对象
		 */
		function isEmpty(obj) {
			return obj == null || obj == "" || "{}" == JSON.stringify(obj);
		}

		mj.extend({
			getBasePath : function(type) {
				return basePath("travel/");
			},
			getCssPath : function() {
				return basePath("static/mobile/css/");
			},
			getJsPath : function() {
				return basePath("static/mobile/js/");
			},
			getImgPath : function() {
				mj.getJsPath();
				return basePath("static/mobile/img/");
			},
			isArray : function(o) {
				return type(o);
			},
			type : function(o) {
				return o == null ? String(o) : clazz[toString.call(o)] || "object";
			},
			initParam : function(a) {
				var s = [], add = function(key, value) {
					s[s.length] = encodeURIComponent(key) + "=" + encodeURIComponent(value);
				};

				// If an array was passed in, assume that it is an array of form
				// elements.
				// if (jQuery.isArray(a) || (a.jquery &&
				// !jQuery.isPlainObject(a)))
				// {
				// // Serialize the form elements
				// jQuery.each(a, function() {
				// add(this.name, this.value);
				// });
				//
				// } else {
				for ( var prefix in a) {
					buildParams(prefix, a[prefix], add);
				}
				// }

				// Return the resulting serialization
				return s.join("&").replace(r20, "+");
			}
		});

		function basePath(type) {
			var location = (window.location + '').split('/');
			var basePath;
			if(location[2]=="localhost:8080"){
				basePath = location[0] + '//' + location[2] + '/' + location[3] + "/";
			}else{
				basePath = location[0] + '//' + location[2] + '/';
			}
			if (!isEmpty(type)) {
				basePath += type;
			}
			return basePath;
		}

		function buildParams(prefix, obj, add) {
			// if (jQuery.isArray(obj)) {
			// // Serialize array item.
			// jQuery.each(obj, function(i, v) {
			// if (traditional || rbracket.test(prefix)) {
			// // Treat each array item as a scalar.
			// add(prefix, v);
			//
			// } else {
			// // If array item is non-scalar (array or object), encode
			// // its
			// // numeric index to resolve deserialization ambiguity
			// // issues.
			// // Note that rack (as of 1.0.0) can't currently
			// // deserialize
			// // nested arrays properly, and attempting to do so may
			// // cause
			// // a server error. Possible fixes are to modify rack's
			// // deserialization algorithm or to provide an option or
			// // flag
			// // to force array serialization to be shallow.
			// buildParams(prefix + "[" + (typeof v === "object" ? i : "") +
			// "]", v, add);
			// }
			// });
			//
			// } else
			if (mj.type(obj) === "object") {
				// Serialize object item.
				for ( var name in obj) {
					buildParams(prefix + "[" + name + "]", obj[name], add);
				}

			} else {
				// Serialize scalar item.
				add(prefix, obj);
			}
		}

		var r20 = /%20/g, clazz = {
			"[object Object]" : "object",
			"[object String]" : "string",
			"[object Number]" : "number",
			"[object Array]" : "array",
			"[object Function]" : "function",
			"[object Boolean]" : "boolan"
		};

		return mj;
	})();

	mj.extend({
		getPaths : function() {
			var jsPath = mj.getJsPath();
			return {
				"jquery" : [ jsPath + "common/jquery_1.7.2" ],
				"angular" : [ jsPath + "common/angular" ],
				"ui-router" : [ jsPath + "common/angular-ui-router" ]
			};
		},
		shim : {
			"angular" : {
				exports : "angular"
			},
			"jquery" : {
				exports : "jquery"
			},
			'ui-router' : {
				deps : [ "angular" ],
				exports : 'ui-router'
			}
		},
		page : function page(pageNum) {
			var form = document.getElementById("form");
			var input = document.getElementById("pageNum");
			input.setAttribute("value", pageNum);
			form.submit();
		}
	});
	w.lt = mj;
})(window)

$(function(){
	try{
		$("img.imglazy").lazyload();
	}catch(e){
		//捕捉异常，可能有某个页面没有加图片的延迟加载
	}
})