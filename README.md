**[简体中文](./README.CN.md) | English**

<p align="center">
    <a href="https://github.com/lyzsk/food-buzz/blob/master/LICENSE">
        <img src="https://img.shields.io/github/license/lyzsk/food-buzz.svg?style=plastic&logo=github" />
    </a>
    <a href="https://github.com/lyzsk/food-buzz/members">
        <img src="https://img.shields.io/github/forks/lyzsk/food-buzz.svg?style=plastic&logo=github" />
    </a>
    <a href="https://github.com/lyzsk/food-buzz/stargazers">
        <img src="https://img.shields.io/github/stars/lyzsk/food-buzz.svg?style=plastic&logo=github" />
    </a>
</p>

# FoodBuzz

> **_If you like this project or it helps you in some way, don't forget to star._** :star:

This is `version-1.0` branch, it's a demo for this project, developed by Vue 2.x, SpringBoot 2.7.x.

To check full version of this project:

```git
git checkout master
```

# Features

-   **Web Management System** for admins:

    -   [x] **admin login/logout** with `{username}`, `{password}`
    -   [x] **employee management** with `{add employee}`, `employee info listing/paging/searching`, `{update employee info}`, `{enable/disable status}`,
    -   [x] **dish/setmeal category management** with `{add dish/setmeal categories}`, `{category info listing/paging}`, `{update dish/setmeal category info}`, `{delete category info}`
    -   [x] **dish management** with `{add dish}`, `{dish info listing/paging/searching}`, `{update dish info}`, `{enable/disable dish status by one and by batch}`, `{delete dish by one and batch}`
    -   [x] **setmeal management** with `{add setmeal}`, `{setmeal info listing/paging/searching}`, `{update setmeal info}`, `{enable/disable setmeal status by one and by batch}`, `{delete setmeal by one and batch}`
    -   [ ] **order management**

-   **Mobile App** for users:

    -   [x] **user login/logout/auto-register** with `{phone number}`, `{SMS code}`
    -   [x] **order module** with `{add dish/setmeal with specification(optional) into shopping cart}`, `{dish/setmeal info listing}`
    -   [ ] **user center module** with `{address book management}`, `{history/recent orders listing/searching}`
    -   [x] **shopping cart module** with `{add/sub items}`, `{clean shopping cart}`, `{amount of consumption preview}`
    -   [x] **payment module** with `{wechat/alipay}`, `{add remark}`, `{listing oder details}`

-   **common modules**

    -   [x] **image upload/download** for `{dish/setmeal image upload/download}`
    -   [ ] **redis** caching for `{SMS code}`, `{menu info}`
    -   [ ] **chatting module** for `{client-to-seller/seller-to-rider online chatting}`

# LICENSE

[MIT LICENSE] Copyright (c) 2022 lyzsk

[mit license]: https://github.com/lyzsk/food-buzz/blob/master/LICENSE
