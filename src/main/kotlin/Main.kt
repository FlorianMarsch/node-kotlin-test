external fun require(module:String):dynamic

fun main(args: Array<String>) {
    println("Hello JavaScript!")

    val express = require("express")
    val app = express()

    val bodyParser = require("body-parser");
    app.use(bodyParser.json());
    app.use(bodyParser.urlencoded());

    app.get("/", { req, res ->
        res.type("text/plain")
        val messageText = HelloWorld().greeting
        res.send(Message(messageText))
    })


    app.post("/", { req, res ->

        var hello : Int? = req?.body?.hello as Int
        hello = hello?.plus(1) 
        println("hello $hello")

        res.type("application/json")
        res.send(req.body)
    })

    // Error Routs (ALWAYS Keep this as the last routes)
    // The 404 Route 
    app.use('*', { req, res-> 
        res.status(404).send(Error("not found"));
    })

    // The 500 Route
    app.use({error, req, res, next-> 
        error?.stack?.run {
            println(error?.stack?.toString())
           res.status(error?.status ?: 500).send(Error(error?.stack?.split('\n')[0].toString() ))
        } 
    })

    app.listen(3000, {
        println("Listening on port 3000")
    })
}

data class Error(val text : String)
data class Message(val text : String)