<div>

    <#list users as user>
        <p>${user.name}</p>
        <div>
                <img src="${user.url}"/>
        </div>

        <br>
    </#list>

</div>