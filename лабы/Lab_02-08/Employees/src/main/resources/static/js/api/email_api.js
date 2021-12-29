let token=localStorage.getItem("token");
let curUrl =  document.URL.split('/')
let employee_id = curUrl[4]

async function onEmailLoad(){
    await getEmployeeById(employee_id, token).then(data=> {
        employeeInfo.innerHTML = `Employee: ${data.firstName} ${data.lastNname} <br> 
                Email: ${data.email} <br>`;
    })
}
//TODO sendmessage post
async function sendMessage(data) {
    let messageText = document.getElementById("messageText").value;
    let err = document.getElementById("errorMessage").value;
        fetch('/user/sendMessage',{
            method:'POST',
            headers:{'Authorization': `Bearer ${token}`,
                'Content-Type':'application/json'},
            body:JSON.stringify({
                employee: employee_id,
                message: messageText
            })
        }).then(result=>{
            if(result.ok){
                alert("Message sent successfully");
                window.location.replace('/index');
            }
            else {
                err.innerHTML = "Smth gone wrong"
            }
        })
}