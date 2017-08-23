webpackJsonp([1],{

/***/ "../../../../../src async recursive":
/***/ (function(module, exports) {

function webpackEmptyContext(req) {
	throw new Error("Cannot find module '" + req + "'.");
}
webpackEmptyContext.keys = function() { return []; };
webpackEmptyContext.resolve = webpackEmptyContext;
module.exports = webpackEmptyContext;
webpackEmptyContext.id = "../../../../../src async recursive";

/***/ }),

/***/ "../../../../../src/app/app-routing.module.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("../../../router/@angular/router.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__users_users_component__ = __webpack_require__("../../../../../src/app/users/users.component.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppRoutingModule; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};



var routes = [
    {
        path: '', component: __WEBPACK_IMPORTED_MODULE_2__users_users_component__["a" /* UsersComponent */]
    }
];
var AppRoutingModule = (function () {
    function AppRoutingModule() {
    }
    return AppRoutingModule;
}());
AppRoutingModule = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["b" /* NgModule */])({
        imports: [__WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* RouterModule */].forRoot(routes)],
        exports: [__WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* RouterModule */]]
    })
], AppRoutingModule);

//# sourceMappingURL=app-routing.module.js.map

/***/ }),

/***/ "../../../../../src/app/app.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/app.component.html":
/***/ (function(module, exports) {

module.exports = "<nav class=\"navbar navbar-inverse\">\r\n  <div class=\"container-fluid\">\r\n    <div class=\"navbar-header\">\r\n      <button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\" data-target=\"#demo\">\r\n        <span class=\"glyphicon glyphicon-menu-hamburger\"></span>\r\n      </button>\r\n      <a class=\"navbar-brand\" style=\"padding: 0 0 0 0\" href=\"#\">\r\n        <img class=\"img-responsive\" height=\"64\" width=\"64\" src=\"static/img/moving.png\" alt=\"Moving\"/>\r\n      </a>\r\n    </div>\r\n\r\n    <div class=\"collapse navbar-collapse\" id=\"demo\">\r\n      <ul class=\"nav navbar-nav\">\r\n        <li class=\"active\"><a href=\"#\">Current Tasks</a></li>\r\n        <li><a href=\"#\">Dashboard</a></li>\r\n        <li class=\"dropdown\">\r\n          <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">Management <span class=\"caret\"></span></a>\r\n          <ul class=\"dropdown-menu\">\r\n            <li class=\"dropdown-header\">Daily Activities</li>\r\n            <li><a href=\"#\">Champin</a></li>\r\n            <li><a href=\"#\">Coupon</a></li>\r\n            <li><a href=\"#\">Price</a></li>\r\n            <li><a href=\"#\">Orders</a></li>\r\n            <li class=\"divider\"></li>\r\n            <li class=\"dropdown-header\">Objects Management</li>\r\n            <li><a href=\"#\">Cars</a></li>\r\n            <li><a href=\"#\">Bicicyles</a></li>\r\n            <li><a href=\"#\">Chargings</a></li>\r\n            <li><a href=\"#\">Umbrellas</a></li>\r\n            <li class=\"divider\"></li>\r\n            <li class=\"dropdown-header\">User Management</li>\r\n            <li><a routerLink=\"/userslist\">Users</a></li>\r\n            <li class=\"divider\"></li>\r\n            <li class=\"dropdown-header\">Admin Management</li>\r\n            <li><a href=\"#\">Employees</a></li>\r\n            <li><a href=\"#\">Roles</a></li>\r\n            <li><a href=\"#\">Stations</a></li>\r\n          </ul>\r\n        </li>\r\n      </ul>\r\n      <!--form class=\"navbar-form\">\r\n        <div class=\"form-group\">\r\n          <input type=\"text\" class=\"form-control\" placeholder=\"Search\"  />\r\n          <button type=\"submit\" class=\"btn btn-default\">Search</button>\r\n        </div>\r\n      </form-->\r\n      <!-- c:url value=\"${request.contextPath}/logout\" var=\"theAction\"/ -->\r\n      <ul class=\"nav navbar-nav navbar-right\">\r\n        <li>\r\n          <a href=\"#\"><span class=\"glyphicon glyphicon-user\"></span> {{userName}}</a>\r\n        </li>\r\n        <li class=\"navbar-form\">\r\n            <button (click)=\"logout()\" class=\"btn btn-success\"><span class=\"glyphicon glyphicon-log-out\"></span>SignOut</button>\r\n        </li>\r\n      </ul>\r\n    </div>\r\n  </div>\r\n</nav>\n\n<div class=\"workArea\">\n  <router-outlet></router-outlet>\n</div>\n"

/***/ }),

/***/ "../../../../../src/app/app.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__users_user_service__ = __webpack_require__("../../../../../src/app/users/user.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_common__ = __webpack_require__("../../../common/@angular/common.es5.js");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var AppComponent = (function () {
    function AppComponent(userService, location) {
        this.userService = userService;
        this.location = location;
        this.title = 'Moving Demo';
        this.userName = 'Shijin';
    }
    AppComponent.prototype.logout = function () {
        this.userService.logout();
    };
    return AppComponent;
}());
AppComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["_11" /* Component */])({
        selector: 'app-root',
        template: __webpack_require__("../../../../../src/app/app.component.html"),
        styles: [__webpack_require__("../../../../../src/app/app.component.css")]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__users_user_service__["a" /* UserService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__users_user_service__["a" /* UserService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__angular_common__["g" /* Location */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__angular_common__["g" /* Location */]) === "function" && _b || Object])
], AppComponent);

var _a, _b;
//# sourceMappingURL=app.component.js.map

/***/ }),

/***/ "../../../../../src/app/app.module.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__ = __webpack_require__("../../../platform-browser/@angular/platform-browser.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__users_users_module__ = __webpack_require__("../../../../../src/app/users/users.module.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__app_routing_module__ = __webpack_require__("../../../../../src/app/app-routing.module.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__app_component__ = __webpack_require__("../../../../../src/app/app.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__angular_http__ = __webpack_require__("../../../http/@angular/http.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__users_user_service__ = __webpack_require__("../../../../../src/app/users/user.service.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppModule; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};







var AppModule = (function () {
    function AppModule() {
    }
    return AppModule;
}());
AppModule = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_1__angular_core__["b" /* NgModule */])({
        declarations: [
            __WEBPACK_IMPORTED_MODULE_4__app_component__["a" /* AppComponent */]
        ],
        imports: [
            __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__["a" /* BrowserModule */],
            __WEBPACK_IMPORTED_MODULE_2__users_users_module__["a" /* UsersModule */],
            __WEBPACK_IMPORTED_MODULE_5__angular_http__["a" /* HttpModule */],
            __WEBPACK_IMPORTED_MODULE_3__app_routing_module__["a" /* AppRoutingModule */]
        ],
        providers: [__WEBPACK_IMPORTED_MODULE_6__users_user_service__["a" /* UserService */]],
        bootstrap: [__WEBPACK_IMPORTED_MODULE_4__app_component__["a" /* AppComponent */]]
    })
], AppModule);

//# sourceMappingURL=app.module.js.map

/***/ }),

/***/ "../../../../../src/app/users/adduser/adduser.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, ".panel {\r\n  background-color: inherit;\r\n}\r\n\r\ninput[type=checkbox] {\r\n  margin-top: 8px;\r\n}\r\n\r\n.alert {\r\n  padding: 0 0 0 10px;\r\n}\r\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/users/adduser/adduser.component.html":
/***/ (function(module, exports) {

module.exports = "<div class=\"container-fluid\">\n  <div class=\"row\">\n    <div class=\"col-sm-offset-4 col-sm-4\">\n      <div class=\"panel panel-default\">\n        <div class=\"panel-body\">\n\n          <form class=\"form-horizontal\" commandName=\"animal\" #userForm=\"ngForm\">\n            <div class=\"form-group\">\n              <label class=\"col-md-3 control-label\">Id:</label>\n              <label class=\"col-md-2 control-label\">{{newUser.id?newUser.id:'N/A'}}</label>\n            </div>\n            <div class=\"form-group\">\n              <label class=\"col-md-3 control-label\">Company:</label>\n              <div class=\"col-md-9\">\n                {{this.user.company}}\n                <input id=\"company\" name=\"company\" [(ngModel)]=\"newUser.company\" required\n                class=\"form-control\" placeholder=\"Company Name\" #company=\"ngModel\">\n                <div *ngIf=\"company.invalid && (company.dirty || company.touched)\"\n                  class=\"alert alert-danger\">\n                  <div *ngIf=\"company.errors.required\">\n                    Company is required.\n                  </div>\n                </div>\n              </div>\n            </div>\n            <div class=\"form-group\">\n              <label class=\"col-md-3 control-label\">Name:</label>\n              <div class=\"col-md-9\">\n                <input id=\"name\" name=\"name\" type=\"text\" [(ngModel)]=\"newUser.username\" required\n                  class=\"form-control\" placeholder=\"User Name\" #name=\"ngModel\">\n                  <div *ngIf=\"name.invalid && (name.dirty || name.touched)\"\n                    class=\"alert alert-danger\">\n                    <div *ngIf=\"name.errors.required\">\n                      Username is required.\n                    </div>\n                  </div>\n              </div>\n            </div>\n            <div class=\"form-group\">\n              <label class=\"col-md-3 control-label\">Roles:</label>\n              <div class=\"col-md-9\">\n                 <label class=\"checkbox-inline\">\n                   <input type=\"checkbox\" [(ngModel)]=\"this.role_user\"\n                      name=\"ROLE_USER\" value=\"USER\" #user=\"ngModel\"> User\n                 </label>\n                 <label class=\"checkbox-inline\">\n                   <input type=\"checkbox\" [(ngModel)]=\"this.role_admin\"\n                      name=\"ROLE_ADMIN\" value=\"ADMIN\" #admin=\"ngModel\"> Admin\n                 </label>\n                 <div *ngIf=\"((user.dirty || user.touched) || (admin.dirty || admin.touched)) && !admin.value && !user.value \"\n                   class=\"alert alert-danger\">\n                     Roles is required.\n                 </div>\n              </div>\n            </div>\n            <div class=\"form-group\">\n              <div class=\"col-md-offset-3 col-md-9\">\n                <button type=\"submit\" class=\"btn btn-default\"\n                (click)=\"addUser()\" [disabled]=\"userForm.invalid || !admin.value && !user.value \">Save</button>\n              </div>\n            </div>\n          </form>\n        </div>\n      </div>\n    </div>\n  </div>\n</div>\n"

/***/ }),

/***/ "../../../../../src/app/users/adduser/adduser.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__user__ = __webpack_require__("../../../../../src/app/users/user.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__user_service__ = __webpack_require__("../../../../../src/app/users/user.service.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AdduserComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var AdduserComponent = (function () {
    function AdduserComponent(userService) {
        this.userService = userService;
        this.role_user = false;
        this.role_admin = false;
        this.newUser = new __WEBPACK_IMPORTED_MODULE_1__user__["a" /* User */]();
    }
    AdduserComponent.prototype.ngOnInit = function () {
    };
    AdduserComponent.prototype.addUser = function () {
        var _this = this;
        this.getUserRoles();
        // var str = 'company=' + this.newUser.company;
        // str += ', name=' + this.newUser.username;
        // str += ', roles=' + this.newUser.roles;
        // str += ', id=' + this.newUser.id;
        // alert("str=" + str);
        this.userService.addUser(this.newUser).then(function (user) {
            _this.newUser = user;
            console.log("user id: " + user.id);
        });
    };
    AdduserComponent.prototype.getUserRoles = function () {
        console.log(this.newUser.company);
        this.newUser.roles = '';
        if (this.role_user) {
            this.newUser.roles = 'ROLE_USER';
        }
        if (this.role_user && this.role_admin) {
            this.newUser.roles += ', ROLE_ADMIN';
        }
        else if (this.role_admin) {
            this.newUser.roles += 'ROLE_ADMIN';
        }
    };
    return AdduserComponent;
}());
AdduserComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["_11" /* Component */])({
        selector: 'app-adduser',
        template: __webpack_require__("../../../../../src/app/users/adduser/adduser.component.html"),
        styles: [__webpack_require__("../../../../../src/app/users/adduser/adduser.component.css")]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_2__user_service__["a" /* UserService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__user_service__["a" /* UserService */]) === "function" && _a || Object])
], AdduserComponent);

var _a;
//# sourceMappingURL=adduser.component.js.map

/***/ }),

/***/ "../../../../../src/app/users/user.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__("../../../http/@angular/http.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_add_operator_toPromise__ = __webpack_require__("../../../../rxjs/add/operator/toPromise.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_add_operator_toPromise___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_rxjs_add_operator_toPromise__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return UserService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var UserService = (function () {
    function UserService(http) {
        this.http = http;
        this.logoutUrl = 'logout';
        this.usersListUrl = 'userslist';
        this.usersListCountUrl = 'userslistcount';
        this.userUrl = 'user';
        this.headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["b" /* Headers */]({ 'Content-Type': 'application/json' });
    }
    UserService.prototype.addUser = function (user) {
        console.log("UserService.addUser()...");
        return this.http.post(this.userUrl, JSON.stringify(user), { headers: this.headers }).toPromise()
            .then(function (response) {
            console.log("response = " + response);
            return response.json();
        })
            .catch(this.handleError);
    };
    UserService.prototype.getUserListCount = function (param) {
        console.log("UserService.getUserList()...");
        return this.http.post(this.usersListCountUrl, JSON.stringify(param), { headers: this.headers }).toPromise()
            .then(function (response) {
            console.log("response = " + response);
            return response.json();
        })
            .catch(this.handleError);
    };
    UserService.prototype.getUserList = function (param) {
        console.log("UserService.getUserList()...");
        return this.http.post(this.usersListUrl, JSON.stringify(param), { headers: this.headers }).toPromise()
            .then(function (response) {
            console.log("response = " + response);
            return response.json();
        })
            .catch(this.handleError);
        // return [
        //   {id: 1, name:'shijin', roles: 'User'},
        //   {id: 2, name:'jeri', roles: 'Admin, User'}
        // ];
    };
    UserService.prototype.logout = function () {
        console.log("logout...");
        this.http.post(this.logoutUrl, null).toPromise()
            .then(function (response) {
            console.log("response=" + response);
        }).catch(this.handleError);
        console.log("logout...end");
    };
    UserService.prototype.handleError = function (error) {
        console.error("An error occurred ", error);
        return Promise.reject(error.message || error);
    };
    return UserService;
}());
UserService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["c" /* Injectable */])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Http */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Http */]) === "function" && _a || Object])
], UserService);

var _a;
//# sourceMappingURL=user.service.js.map

/***/ }),

/***/ "../../../../../src/app/users/user.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return User; });
var User = (function () {
    function User() {
    }
    return User;
}());

//# sourceMappingURL=user.js.map

/***/ }),

/***/ "../../../../../src/app/users/users-list/Pagination.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return Pagination; });
var Pagination = (function () {
    function Pagination() {
    }
    return Pagination;
}());

//# sourceMappingURL=Pagination.js.map

/***/ }),

/***/ "../../../../../src/app/users/users-list/UserListQueryParameters.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return UserListQueryParameters; });
var UserListQueryParameters = (function () {
    function UserListQueryParameters() {
    }
    return UserListQueryParameters;
}());

//# sourceMappingURL=UserListQueryParameters.js.map

/***/ }),

/***/ "../../../../../src/app/users/users-list/users-list.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, ".table-hover>tbody>tr:hover {\r\n  color: black;\r\n}\r\n\r\n.center {\r\n  text-align: center;\r\n}\r\n\r\n.btn-adduser {\r\n  margin-top: 20px;\r\n  text-align: right;\r\n}\r\n\r\nh3 > .badge {\r\n  background-color: black;\r\n}\r\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/users/users-list/users-list.component.html":
/***/ (function(module, exports) {

module.exports = "\n<div [hidden]=\"!users || users.length <= 0\">\n  <div class=\"row\">\n    <h3 class=\"col-md-10\">User List <span class=\"badge\">{{this.pagination.total == 0?\"\":this.pagination.total}}</span></h3>\n    <div class=\"col-md-2 btn-adduser\">\n      <button class=\"btn btn-default\" (click)=\"gotoAddUserPage()\"><span class=\"glyphicon glyphicon-plus\"></span> Add new user</button>\n    </div>\n  </div>\n  <table class=\"table table-bordered table-hover\">\n    <tr>\n      <th class=\"center\">Id</th>\n      <th class=\"center\">Company</th>\n      <th class=\"center\">Name</th>\n      <th class=\"center\">Roles</th>\n      <th class=\"center\">Enabled</th>\n      <th class=\"center\">Edit</th>\n      <th class=\"center\">Delete</th>\n    </tr>\n    <tr *ngFor=\"let user of users\">\n      <td>{{user.id}}</td>\n      <td>{{user.company}}</td>\n      <td>{{user.username}}</td>\n      <td>{{user.roles}}</td>\n      <td></td>\n      <td class=\"center\"><span class=\"glyphicon glyphicon-edit\"></span></td>\n      <td class=\"center\"><span class=\"glyphicon glyphicon-remove\"></span></td>\n    </tr>\n  </table>\n  <nav class=\"center\" [hidden]=\"this.pagination.total <= 0\">\n      <ul class=\"pagination\">\n        <li [class.disabled]=\"this.pagination.current == 1\"><a href=\"javascript:void(0)\" (click)=\"gotoPage(1)\"><span class=\"glyphicon glyphicon-fast-backward\"></span></a></li>\n        <li [class.disabled]=\"this.pagination.current == 1\"><a href=\"javascript:void(0)\" (click)=\"gotoPage(this.pagination.current - 1)\"><span class=\"glyphicon glyphicon-backward\"></span></a></li>\n        <li *ngFor=\"let index of [1, 2, 3, 4, 5]\" [class.active]=\"index == this.getCurrentPosition()\">\n          <a href=\"javascript:void(0)\" (click)=\"gotoPage(getPageNumber(index))\" [hidden]=\"getPageNumber(index) == -1\">{{getPageNumber(index)}}</a>\n        </li>\n        <li [class.disabled]=\"this.pagination.current == this.pagination.totalPage\"><a href=\"javascript:void(0)\"  (click)=\"gotoPage(this.pagination.current + 1)\"><span class=\"glyphicon glyphicon-forward\"></span></a></li>\n        <li [class.disabled]=\"this.pagination.current == this.pagination.totalPage\"><a href=\"javascript:void(0)\"  (click)=\"gotoPage(this.pagination.totalPage)\"><span class=\"glyphicon glyphicon-fast-forward\"></span></a></li>\n      </ul>\n  </nav>\n</div>\n"

/***/ }),

/***/ "../../../../../src/app/users/users-list/users-list.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__user_service__ = __webpack_require__("../../../../../src/app/users/user.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__UserListQueryParameters__ = __webpack_require__("../../../../../src/app/users/users-list/UserListQueryParameters.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__Pagination__ = __webpack_require__("../../../../../src/app/users/users-list/Pagination.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__angular_router__ = __webpack_require__("../../../router/@angular/router.es5.js");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return UsersListComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var UsersListComponent = (function () {
    function UsersListComponent(userService, router) {
        this.userService = userService;
        this.router = router;
        this.param = new __WEBPACK_IMPORTED_MODULE_2__UserListQueryParameters__["a" /* UserListQueryParameters */]();
        this.param.pageParam = new __WEBPACK_IMPORTED_MODULE_3__Pagination__["a" /* Pagination */]();
        this.param.pageParam.current = 1;
        this.param.pageParam.countPerPage = 10;
        this.pagination = new __WEBPACK_IMPORTED_MODULE_3__Pagination__["a" /* Pagination */]();
        this.pagination.current = 1;
        this.pagination.countPerPage = 10;
        this.pagination.total = 332;
    }
    UsersListComponent.prototype.ngOnInit = function () {
        console.log("UsersListComponent is init...");
        //this.users = USERS;
        this.getUserList(this.param);
    };
    UsersListComponent.prototype.getUserList = function (param) {
        var _this = this;
        console.log("UsersListComponent.getUserList()...");
        this.userService.getUserList(param).then(function (users) {
            // console.log("users: " + users);
            _this.users = users;
        });
        this.userService.getUserListCount(param).then(function (count) {
            _this.pagination.total = count;
        });
    };
    /**
    * position: -1:Frist, 0:Previous, 1~5: five buttons, 6: Next, 7:Last
    * return 0: hidden, anyNumber: display the value
    */
    UsersListComponent.prototype.getPageNumber = function (position) {
        var pos;
        switch (position) {
            case 1:
                pos = this.getFirstPageNumber();
                break;
            case 2:
            case 3:
            case 4:
            case 5:
                pos = this.getPageNumber(position - 1) + 1;
                if (pos == 0 || pos > this.pagination.totalPage) {
                    pos = -1;
                }
                break;
        }
        // console.log('pos(' + position + ')=' + pos);
        return pos;
    };
    UsersListComponent.prototype.gotoPage = function (page) {
        this.param.pageParam.current = page;
        this.pagination.current = page;
        console.log("current page = " + page);
        this.getUserList(this.param);
    };
    UsersListComponent.prototype.getFirstPageNumber = function () {
        var totalPage = Math.ceil(this.pagination.total / this.pagination.countPerPage);
        this.pagination.totalPage = totalPage;
        //console.log('totalPage=' + totalPage);
        if (totalPage < 5) {
            return 1;
        }
        else if (totalPage - this.pagination.current < 3) {
            return totalPage - 4;
        }
        else if (this.pagination.current < 4) {
            return 1;
        }
        else {
            return this.pagination.current - 2;
        }
    };
    UsersListComponent.prototype.getCurrentPosition = function () {
        return this.pagination.current - this.getFirstPageNumber() + 1;
    };
    UsersListComponent.prototype.gotoAddUserPage = function () {
        var link = ['/adduser'];
        this.router.navigate(link);
    };
    return UsersListComponent;
}());
UsersListComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["_11" /* Component */])({
        selector: 'app-users-list',
        template: __webpack_require__("../../../../../src/app/users/users-list/users-list.component.html"),
        styles: [__webpack_require__("../../../../../src/app/users/users-list/users-list.component.css")]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__user_service__["a" /* UserService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__user_service__["a" /* UserService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_4__angular_router__["b" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__angular_router__["b" /* Router */]) === "function" && _b || Object])
], UsersListComponent);

var _a, _b;
//# sourceMappingURL=users-list.component.js.map

/***/ }),

/***/ "../../../../../src/app/users/users-routing.module.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("../../../router/@angular/router.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__users_component__ = __webpack_require__("../../../../../src/app/users/users.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__users_list_users_list_component__ = __webpack_require__("../../../../../src/app/users/users-list/users-list.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__adduser_adduser_component__ = __webpack_require__("../../../../../src/app/users/adduser/adduser.component.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return UsersRoutingModule; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};





var routes = [
    { path: 'users', component: __WEBPACK_IMPORTED_MODULE_2__users_component__["a" /* UsersComponent */] },
    { path: 'userslist', component: __WEBPACK_IMPORTED_MODULE_3__users_list_users_list_component__["a" /* UsersListComponent */] },
    { path: 'adduser', component: __WEBPACK_IMPORTED_MODULE_4__adduser_adduser_component__["a" /* AdduserComponent */] },
];
var UsersRoutingModule = (function () {
    function UsersRoutingModule() {
    }
    return UsersRoutingModule;
}());
UsersRoutingModule = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["b" /* NgModule */])({
        imports: [__WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* RouterModule */].forChild(routes)],
        exports: [__WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* RouterModule */]]
    })
], UsersRoutingModule);

//# sourceMappingURL=users-routing.module.js.map

/***/ }),

/***/ "../../../../../src/app/users/users.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/users/users.component.html":
/***/ (function(module, exports) {

module.exports = "<p>\n  Home Page\n</p>\n"

/***/ }),

/***/ "../../../../../src/app/users/users.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return UsersComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var UsersComponent = (function () {
    function UsersComponent() {
    }
    UsersComponent.prototype.ngOnInit = function () {
    };
    return UsersComponent;
}());
UsersComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["_11" /* Component */])({
        selector: 'app-users',
        template: __webpack_require__("../../../../../src/app/users/users.component.html"),
        styles: [__webpack_require__("../../../../../src/app/users/users.component.css")]
    }),
    __metadata("design:paramtypes", [])
], UsersComponent);

//# sourceMappingURL=users.component.js.map

/***/ }),

/***/ "../../../../../src/app/users/users.module.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_common__ = __webpack_require__("../../../common/@angular/common.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_common_http__ = __webpack_require__("../../../common/@angular/common/http.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__users_routing_module__ = __webpack_require__("../../../../../src/app/users/users-routing.module.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__angular_forms__ = __webpack_require__("../../../forms/@angular/forms.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__users_component__ = __webpack_require__("../../../../../src/app/users/users.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__users_list_users_list_component__ = __webpack_require__("../../../../../src/app/users/users-list/users-list.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__adduser_adduser_component__ = __webpack_require__("../../../../../src/app/users/adduser/adduser.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__user_service__ = __webpack_require__("../../../../../src/app/users/user.service.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return UsersModule; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};









var UsersModule = (function () {
    function UsersModule() {
    }
    return UsersModule;
}());
UsersModule = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["b" /* NgModule */])({
        imports: [
            __WEBPACK_IMPORTED_MODULE_1__angular_common__["e" /* CommonModule */],
            __WEBPACK_IMPORTED_MODULE_4__angular_forms__["a" /* FormsModule */],
            __WEBPACK_IMPORTED_MODULE_2__angular_common_http__["a" /* HttpClientModule */],
            __WEBPACK_IMPORTED_MODULE_3__users_routing_module__["a" /* UsersRoutingModule */]
        ],
        declarations: [__WEBPACK_IMPORTED_MODULE_5__users_component__["a" /* UsersComponent */], __WEBPACK_IMPORTED_MODULE_6__users_list_users_list_component__["a" /* UsersListComponent */], __WEBPACK_IMPORTED_MODULE_7__adduser_adduser_component__["a" /* AdduserComponent */]],
        providers: [__WEBPACK_IMPORTED_MODULE_8__user_service__["a" /* UserService */]]
    })
], UsersModule);

//# sourceMappingURL=users.module.js.map

/***/ }),

/***/ "../../../../../src/environments/environment.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return environment; });
// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.
// The file contents for the current environment will overwrite these during build.
var environment = {
    production: false
};
//# sourceMappingURL=environment.js.map

/***/ }),

/***/ "../../../../../src/main.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__ = __webpack_require__("../../../platform-browser-dynamic/@angular/platform-browser-dynamic.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__app_app_module__ = __webpack_require__("../../../../../src/app/app.module.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__environments_environment__ = __webpack_require__("../../../../../src/environments/environment.ts");




if (__WEBPACK_IMPORTED_MODULE_3__environments_environment__["a" /* environment */].production) {
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["a" /* enableProdMode */])();
}
__webpack_require__.i(__WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__["a" /* platformBrowserDynamic */])().bootstrapModule(__WEBPACK_IMPORTED_MODULE_2__app_app_module__["a" /* AppModule */]);
//# sourceMappingURL=main.js.map

/***/ }),

/***/ 0:
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__("../../../../../src/main.ts");


/***/ })

},[0]);
//# sourceMappingURL=main.bundle.js.map