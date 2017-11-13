var A = 3;
var B = 6;
var C = A * B;

//document.getElementById("outputText").innerHTML = "A = " + A + ", B = " + B + ", C = A * B = " + C;

    function testJava(){
        window.webConnector.onData("123888");
    }


    function getData(data){
    window.webConnector.onData("123");
    }

    function exitActivity(){
       window.webConnector.exitActivity();
    }

    function saveNote(){
           window.webConnector.saveNoteToFile();
        }

