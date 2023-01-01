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

This is `v2.0` branch, it's a demo for this project, developed by Vue 2.x, SpringBoot 2.7.x.

The key updates from `v1.0`:

1. Update database design, add logical delete to tables refer to important data, add more fields for third party login identification ids.
2. Refactor frontend for Mobile App, switch from Vue.js to React.js
3. Add configurations for `Sharding-JDBC` framework for **read-write separation**, add nginx configurations for **load balancing**, currently using round robin method.
4. Abandon MyBatisPlus code generator for basic entity, mapper, controller reverse generation, because it will cause many bugs when using snowflake id generation, java.util.LocalDateTime for sql datetime fields.

To check full version of this project:

```git
git checkout master
```

# Table of Contents

1. [Installation](#installation)
2. [Features](#features)

# Installation

## Windows

-   Backend:

    ```
    <!-- 1. start springboot application -->
    mvn clean install
    mvn spring-boot:run

    <!-- 2. visit url in browser-->
    http://localhost:8081/
    ```

-   Frontend:

    ```
    <!-- 1. start react app -->
    npm install
    npm start

    <!-- 2. visit url in browser -->
    http://localhost:3000/
    ```

## Linux

> **IMPORTANT:**
>
> Please install jdk 1.8, maven 3.8.2 or above before backend installation
>
> Please install node.js 18.12.1 or above, npm 8.5.5 or above before frontend installation

# Features

-   **Web Management System** for admins:

    -   [ ] admin login/logout
    -   [ ] employee management
    -   [ ] dish/setmeal category management
    -   [ ] dish management
    -   [ ] setmeal management
    -   [ ] order management

-   **Mobile App** for users:

    -   [ ] user login/logout/auto-register
    -   [ ] order module
    -   [ ] user center module
    -   [ ] shopping cart module
    -   [ ] payment module

-   **common modules**

    -   [ ] image upload/download
    -   [ ] redis
    -   [ ] chatting module

# LICENSE

[MIT LICENSE] Copyright (c) 2022 lyzsk

[mit license]: https://github.com/lyzsk/food-buzz/blob/master/LICENSE
