/*!
 * Remark (http://getbootstrapadmin.com/remark)
 * Copyright 2017 amazingsurge
 * Licensed under the Themeforest Standard Licenses
 */

!function(global,factory){if("function"==typeof define&&define.amd)define("/Plugin/input-group-file",["exports","jquery","Plugin"],factory);else if("undefined"!=typeof exports)factory(exports,require("jquery"),require("Plugin"));else{var mod={exports:{}};factory(mod.exports,global.jQuery,global.Plugin),global.PluginInputGroupFile=mod.exports}}(this,function(exports,_jquery,_Plugin2){"use strict";Object.defineProperty(exports,"__esModule",{value:!0});var _jquery2=babelHelpers.interopRequireDefault(_jquery),_Plugin3=babelHelpers.interopRequireDefault(_Plugin2),NAME="inputGroupFile",InputGroupFile=function(_Plugin){function InputGroupFile(){return babelHelpers.classCallCheck(this,InputGroupFile),babelHelpers.possibleConstructorReturn(this,(InputGroupFile.__proto__||Object.getPrototypeOf(InputGroupFile)).apply(this,arguments))}return babelHelpers.inherits(InputGroupFile,_Plugin),babelHelpers.createClass(InputGroupFile,[{key:"getName",value:function(){return NAME}},{key:"render",value:function(){this.$file=this.$el.find("[type=file]"),this.$text=this.$el.find(".form-control")}},{key:"change",value:function(){var value="";_jquery2.default.each(this.$file[0].files,function(i,file){value+=file.name+", "}),value=value.substring(0,value.length-2),this.$text.val(value)}}],[{key:"api",value:function(){return"change|change"}}]),InputGroupFile}(_Plugin3.default);_Plugin3.default.register(NAME,InputGroupFile),exports.default=InputGroupFile});