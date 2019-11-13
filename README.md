# todoListWebApp
ToDo List Planner using Springboot + ReactJS + Rest API

							

Prerequisite
============
1. Eclipse
2. JDK 8
3. Node.js
4. NPM
5. Maven

Backend setup:
=============
1. Import the supplied maven project to eclipse (todolist-springboot/)
2. Right Click on SpringBootFullstackApplication.java and Run As – Java Application
3. Open a browser and navigate to “http://localhost:8080/tasks. Check whether rest API is working

Frontend setup:
==============
1. Copy front end project to local
2. Open command prompt and run below commands,
	1) create-react-app todolist-react
	2) npm add axios
	3) npm add react-router-dom
	4) npm add formic
	5) npm add bootstrap
3. Copy App.js, index.js, src, component folders from above front-end project to todolist-react/src folder
4. Run below command from “..todolist-react/” location
	a) npm start
5. Open browser and navigate to localhost:3000/tasks
