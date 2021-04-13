INSERT INTO users (uname, passwd, email) VALUES ('Franco', 'my password', 'franco@gmail.com');
INSERT INTO users (uname, passwd, email) VALUES ('DEvans', 'my password', 'evans@gmail.com');
INSERT INTO users (uname, passwd, email) VALUES ('TechWiz1337', 'leeth4xor', 'iamsocool@gmail.com');
INSERT INTO users (uname, passwd, email) VALUES ('BadatCode97', 'superman', 'bac@gmail.com');
INSERT INTO users (uname, passwd, email) VALUES ('LeeroyJenkins', 'wowlord', 'LJ@gmail.com');

INSERT INTO questions (title, body, author) VALUES ('Union of two arrays, without duplicates', 
	'I am trying to find the union of two arrays. Since there cannot be duplicates, normal concatination will not work.', 1
);

INSERT INTO questions (title, body, author) VALUES ('Date Format with different Timezone in Java', 
	'I am confused with Timezone conversions in Java.', 3
);

INSERT INTO questions (title, body, author) VALUES ('Call an input again if the statements become false', 
	'I want to ask the user for input unless m = "1" or "2" if m becomes 1 it will call for fun1() again or if m becomes 2 it will exit the loop. 
    If user give another value except 1 or 2 it ask for the value of m again. 
    
    def fun1():
    a = input("Which one do you want to log?\n"
              1 for Jubayer\n"\
              "2 for Anamul\n"
              "3 for Shrabon\n")
    return a


while True:
    person = fun1()
    if person in {"1", "2", "3"}:
        decision = di_ex()
        if decision == "1":
            diet(person)
        elif decision == "2":
            exercise(person)
        else:
            print("Try again.")
        m = input("Do you want to continue?\n"
                  "1 for Continue\n"
                  "2 for Exit\n")
        if m == "1":
            continue
        else:
            break

    else:
        print("You have failed."
              "Try again.")', 4
);

INSERT INTO questions (title, body, author) VALUES ('Empty innerText in UseEffect React Hook', 
	'I am having trouble understanding how useEffect works. I have a piece of code below:

import React, { useState, useEffect, useLayoutEffect, useRef, createRef } from "react";
import {Link} from "react-router-dom";
import LoadingPage from "../LoadingPage";
import Slideshow from "../SlideShow"
import CircleType from "circletype";

function Home() {
  const categories = ["advance beauty", "beauty", "special effects", "airbrush", "others"]
  const images = useState(
    [[
      "/images/elegant_sugar_skull4.jpg",
      "/images/elegant_sugar_skull3.jpg",
      "/images/elegant_sugar_skull1.jpg"
    ],
    ]);', 5
);

INSERT INTO questions (title, body, author)  VALUES('How do I create a for loop',
'I am writing a program that will require a for loop, can someone please explain to me how to go about creating one', 
2
);

INSERT INTO questions (title, body, author)  VALUES('How do I create a do while loop',
'I am writing a program that will require a do while loop but I am a bit confused where to start, can someone please explain to me how to go about creating one', 
1
);

INSERT INTO answers (question, body, codeBody, author) VALUES (
1, 
'We can start with concatination. Then, loop through the newly formed array, checking for duplicates for every element, and removing them if necessary. The inner loop starts at the end and moves backwards because Array.splice() shifts all the elements after the index by 1. Moving backward means slightly less elements to shift.',
'function union(arr1, arr2) {
    let newArray = arr1.concat(arr2);
    for (let i = 0; i < newArray.length; i++) {
        for (let j = newArray.length - 1; j > i; j--) {
            if (newArray[i] === newArray[j]) {
                newArray.splice(j, 1);
            }
        }
    }
    return newArray;
}',
2
);

INSERT INTO answers (question, body, codeBody, author) VALUES (
1,
'For larger arrays, this is the most effecient way I can think of. It is O(N), assuming you are using a constant time hash set implementation.',
'function union(a1, a2) {
    const usedValues = new Set();
    const newArray = [];
    for (let item of a1) {
        if (!usedValues.has(item)) {
            usedValues.add(item);
            newArray.push(item);
        }
    }
    for (let item of a2) {
        if (!usedValues.has(item)) {
            usedValues.add(item);
            newArray.push(item);
        }
    }
    return newArray;
}',
3
);

INSERT INTO answers (question, body, codeBody, author) VALUES (
4,
'I suspect that [] as second argument in useEffect is the troublemaker. 
It causes the useEffect to be called only once with default props and stats. 
Try removing it:',
' useEffect(() => {
        const slideshow = new Slideshow(document.querySelector(.slideshow));
        const topText = document.querySelectorAll(.bottom);
        console.log(topText[0].innerText);
        topText.forEach(text=>{
          console.log(text.innerText)
          new CircleType(text).radius(384)
        })
  }); ',
1
);

INSERT INTO answers (question, body, codeBody, author) VALUES (
2,
'I recommend you use java.time, the modern Java date and time API, for your date work',

'    LocalDate date = LocalDate.parse("2021-01-31");
     System.out.println(date);',
5
);

INSERT INTO answers (question, body, codeBody, author) VALUES (
3,
'Create another function for Do you want... prompt and put it inside while loop and break the loop if input is 1 or 2.',

'def fun2():

    while True:   
        m = input("Do you want to continue?\n"
             "1 for Continue\n"
             "2 for Exit\n")
        if m in ("1","2"):
            return m',
1
);