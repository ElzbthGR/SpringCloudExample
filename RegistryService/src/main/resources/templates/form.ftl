<#import "/spring.ftl" as spring/>
<script src="/sockjs-1.3.0.js"></script>
<script src="/stomp.js"></script>
<script src="/socket.js"></script>
<script src="/ajax.js"></script>
<script src="/jquery.ajax.js"></script>
<c:url var="home" value="/" scope="request" />



<meta charset="UTF-8">
<div id="msgId"></div>
<div>
    <div>
        <img id="image_cat"/>
    </div>
    <form method="post" name="userObj" action="/registry" id="form">
        <div>
            <label for="name">Username:</label>
            <input id="name" name="name" placeholder="Name"/>
        </div>

        <div>
        </div>

        <div>
            <button type="submit" name="user" id="registry">Registry</button>
        </div>
    </form>
    <button onclick="ajaxGet()" name="cat">Choose cat</button>

</div>
