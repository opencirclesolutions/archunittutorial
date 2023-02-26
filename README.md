# Getting started

### Reference Documentation
For further reference, please consider the following sections:

* [Official ArchUnit documentation](https://www.archunit.org/userguide/html/000_Index.html)
* [Official ArchUnit API](https://javadoc.io/doc/com.tngtech.archunit/archunit/latest/index.html)

### Getting started
The following guide illustrate how to use some features concretely:

* [Getting started with ArchUnit](https://www.archunit.org/getting-started)

Your can run the simple application `TutorialCliApplication` that shows some simple repository and service calls.

Find two examples of architecture tests in:
* `ArchitectureTestSuccess` - demonstrates a test that succeeds
  * Every finder method in a class that implements interface CrudRepository must return either Collection or Optional, because it is a best practise when a method can return null ons should return an optional.
* `ArchitectureTestFailure` - demonstrates a test that fails
  * No class should depend on hibernate, because the architecture decision for this project is that JPA by the use of Spring Data is the chosen data access method.

### Exercises for fun and practice
This tutorial has several basic exercises you can use to practise and learn how to use ArchUnit.
There are several architecture violations in this code that can be found with ArchUnit:
1. Naming violations (branch Solution1)
   1. A *repository* interface class name should end with *"Repository"*
   2. A *service* implementation class name should contain *"Service"*
2. Structure violations (branch Solution2)
   1. Entities should reside within package domain
   2. Repositories should reside in package dataaccess
3. Separation of Concern violations (branch Solution3)
   1. Classes in package dataaccess may not access classes in package services
   2. Classes in package ui may not access classes in package dataaccess

You are encouraged to solve the ArchUnit rules to the previous violations yourself.
The solutions to the exercises can be found in the mentioned git branches.
When you want to compare your solution with the provided solutions just choose git compare with the associated branch.
All solutions can be found in branch SolutionAll.

Have fun and learn!