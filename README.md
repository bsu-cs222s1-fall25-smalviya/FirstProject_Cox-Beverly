#README.md
#Quentin 
#Vaughn

#App.java 

    ASK user to enter a Wikipedia article title
    READ user input and store it in variable 'title'

    CALL function to get Wikipedia data for 'title'
    STORE result in variable 'json'

    CALL function to check for redirects in 'json'
    STORE result in list 'redirects'

    IF 'redirects' is not empty THEN
        FOR EACH redirect in 'redirects'
            PRINT redirect
        END FOR
    END IF

    CALL function to get revisions from 'json'
    STORE result in list 'revisions'

    CALL function to format the 'revisions' list
    STORE result in variable 'formatted'

    PRINT "Recent revisions for [title]"
    PRINT 'formatted'


#Redirect.java 


    DEFINE private variable 'from' as text
    DEFINE private variable 'to' as text

    FUNCTION Constructor(fromInput, toInput)
        SET 'from' = fromInput
        SET 'to' = toInput
    END FUNCTION

    FUNCTION getFrom()
        RETURN 'from'
    END FUNCTION

    FUNCTION getTo()
        RETURN 'to'
    END FUNCTION

    FUNCTION toString()
        RETURN "Redirected from [from] to [to]"
    END FUNCTION

#RevisionFormatter.java

    FUNCTION format(revisionsList)
        CREATE empty text builder called 'sb'

        FOR EACH revision IN revisionsList
            ADD "Revision by " + revision's user TO 'sb'
            ADD " at " + revision's timestamp TO 'sb'
            ADD newline to 'sb'
        END FOR

        RETURN 'sb' as a single string
    END FUNCTION

  #RevisionParser.java

    FUNCTION parseRevisions(jsonData)
        CREATE empty list called 'revisions'

        PARSE jsonData into an object called 'root'
        GET 'pages' object from root["query"]["pages"]

        FOR EACH pageId in pages
            GET 'revisions' array from pages[pageId]

            FOR EACH revisionElement in revisions
                GET 'user' from revisionElement
                GET 'timestamp' from revisionElement
                CREATE new Revision with user and timestamp
                ADD Revision to 'revisions' list
            END FOR
        END FOR

        RETURN 'revisions' list
    END FUNCTION

    FUNCTION parseRedirects(jsonData)
        CREATE empty list called 'redirects'

        PARSE jsonData into an object called 'root'

        IF root["query"] contains "redirects"
            GET 'redirects' array from root["query"]["redirects"]

            FOR EACH redirectElement in redirects
                GET 'from' from redirectElement
                GET 'to' from redirectElement
                CREATE new Redirect with from and to
                ADD Redirect to 'redirects' list
            END FOR
        END IF

        RETURN 'redirects' list
    END FUNCTION

#WikipediaConnection.java

    FUNCTION main()
        CALL connectToWikipedia() AND store result in 'connection'
        CALL readJsonAsStringFrom(connection) AND store result in 'jsonData'
        CALL printRawJson(jsonData)
    END FUNCTION

    FUNCTION connectToWikipedia()
        ENCODE the Wikipedia API URL with:
            - article title "Einstein"
            - revision properties: timestamp and user
            - limit revisions to 4
            - follow redirects

        CONVERT the encoded URL string to a URI
        OPEN a connection to the URI
        SET the User-Agent header to identify the app
        CONNECT to the URL
        RETURN the connection
    END FUNCTION

    FUNCTION readJsonAsStringFrom(connection)
        READ all bytes from the connection's input stream
        CONVERT the bytes to a string using system's default character set
        RETURN the JSON string
    END FUNCTION

    FUNCTION printRawJson(jsonData)

    
#WikipediaReader.java


    FUNCTION read(title)
        TRY
            ENCODE the article title for use in a URL

            BUILD the Wikipedia API URL using:
                - the encoded title
                - format: JSON
                - data: revisions with timestamp and user
                - limit: 15 revisions
                - include redirects

            OPEN a connection to the URL
            SET the User-Agent header to identify the application
            CONNECT to Wikipedia

            READ all data from the connection
            CONVERT data to a string
            RETURN the JSON string

        CATCH any input/output error
            THROW an error message that fetching the article failed
        END TRY
    END FUNCTION



