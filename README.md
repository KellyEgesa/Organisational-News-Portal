#Organisational-News-Portal

#### A REST API for querying and retrieving scoped news and information

#### By **Bartholomew Kelly Egesa**

## Description

A user can create Departments, News which are broken into two:- General News (Information affecting the whole company), Department news (News affecting a certain department) and Users.
A user can see individual users and their details.
A user can see specific information about the Departments.
A user can see all users from a specific Department, the news relating to that department.
A user can be able to Post some news relating to a department.
## Behavior Driven Development

| BEHAVIOR:Our program should handle |                  Input Example When it receives                   |           Output Example It should return           |
| ---------------------------------- | :---------------------------------------------------------------: | :-------------------------------------------------: |
| Add a new User                           |        Inputs:-userName:"Kelly Egesa",positionInCompany: "Manager",departmentId: 1, userRole: "run day to day activities",|               A new user has been added successfully               |
| Add a new Department                            |   Input:-departmentName: "Accounting",departmentDescription: "Assure financial records",|         A department as been added successfully           |
| Add a new General News                            |                Input:-newsInfo: "No work Tomorrow"           |                A new General News as been added successfully                  |
| Add a new Department News                           |           Input:- departmentId: 1,newsInfo: "No work Tomorrow",          |               A new Department has been added successfully              |
| Delete a User                            |               Delete request http://localhost:4567/user/1           |                User is deleted           |
| Delete a Department                          |              Delete request http://localhost:4567/departments/1             |                Department is deleted           |
| Delete a General News                             |         Delete request http://localhost:4567/generalNews/1    |                General News is deleted           |
| Delete a Department News                             |     Delete request http://localhost:4567/departmentNews/1              |                Department News   is deleted           |

## Known Bugs

There are no known bugs

## Setup/Installation Requirements

- Setup git
- Open the terminal application by either clicking on the terminal icon or by clicking Ctrl + alt + T.
- Create a new folder called Organisational-News-Portal by pressing mkdir Organisational-News-Portal and pressing enter.
- Navigate to Organisational-News-Portal by pressing cd Organisational-News-Portal and pressing enter.
- Go to KellyEgesa github user name on the browser, click on repositories, Click on Organisational-News-Portal then click on clone or download option
- Copy paste the given Url
- Press git clone plus the url on the terminal then press center
- On the terminal and press gradle run

## Setup Database
- Install postgresql first.
- Navigate to Organisational-News-Portal by pressing cd Organisational-News-Portal and pressing enter.
- Enter psql < create.sql to setup database.
- To delete database enter psql < drop.sql.

## Technologies Used

- JAVA
- SPARK
- POSTGRESQL

## Support and contact details

You can contact me via Email at kelly.egesa@gmail.com

### License

_M.I.T_
Copyright (c)2020 **KELLY EGESA**
