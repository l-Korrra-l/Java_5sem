let token = localStorage.getItem("token");
let curUrl =  document.URL.split('/')
let employee_id = curUrl[4]

async function onUpdateEmployeeLoad(){
    let token = localStorage.getItem('token');
    let employeeData=document.getElementById('employeeData');
    await getEmployeeByIdForAdmin(employee_id, token).then(data =>{
    employeeData.innerHTML=`
    <input type="hidden" id="id" value="${data.id}"></input>
    <p>Name:</p>
    <input id="name" placeholder="Name" value="${data.firstName}"> </input>
    <p>Surname:</p>
    <input id="surname" placeholder="Surname" value="${data.lastName}"></input>
    <p>Email: </p>
    <input id="email" placeholder="Email" value="${data.email}"></input>
     <p>Age: </p>
    <input id="age" type="number" placeholder="age" value="${data.age}"></input>
     <p>Salary: </p>
    <input id="salary" type="number" step="0.01" placeholder="Salery" value="${data.salary}"></input>`});
}

async function updateEmployee(){
    let errMes = document.getElementById('errMes');
    let token = localStorage.getItem('token');
    let id=document.getElementById('id').value
    let name = document.getElementById('name').value;
    let surname = document.getElementById('surname').value;
    let age = document.getElementById('age').value;
    let email = document.getElementById('email').value;
    let salary = document.getElementById('salary').value;

    if (validateEmployee()) {
        await updateEmployee({
            id: id,
            firstName: name,
            lastName: surname,
            age: age,
            email: email,
            salary: salary
        }, token);
        alert( 'Edited');
    } else {
        alert( 'Invalid data');}
}

function validateEmployee() {
    let nameL = document.getElementById('name').value;
    let surnameL = document.getElementById('surname').value;
    let ageL = document.getElementById('age').value;
    let emailL = document.getElementById('email').value;
    let salary = document.getElementById('salary').value;

    if (!(nameL >= 2 && nameL <= 20)) {
        return false;
    }
    if (!(surnameL >= 2 && surnameL <= 20)) {
        return false;
    }
    if (!(emailL >= 2 && emailL <= 20)) {
        return false;
    }
    if (!(ageL >= 0)) {
        return false;
    }
    if (salary === null) {
        return false;
    }
    return true;
}



async function updateEmployee(data, token) {
    return await fetch("/admin/updateEmployee", {
        method: 'PUT',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body: JSON.stringify(data)

    });
}