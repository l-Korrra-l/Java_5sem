//------------------message page
async function generateSearch() {
    let token = localStorage.getItem('token');
    let inputResult_origin = document.getElementById('searchEmployee').value;
    let inputResult=inputResult_origin;
    if(inputResult_origin.length>0){
        inputResult = inputResult_origin[0].toUpperCase() + inputResult_origin.slice(1);}
    let list = document.getElementById('allEmployees');
    list.innerHTML = '';
    if(inputResult.length===0){
        await loadEmployees();
    }else{
        let listProject = await getAllEmployees(token);
        let searchResults=document.getElementById('searchResults');
        list.innerHTML+=`<tr>
        <th>Name</th>
        <th>Surname</th>
        <th>Age</th>
        <th>Salary</th>
        <th>Email</th>
    </tr>`;
        for (let i = 0; i < listProject.length; i++) {
            if (inputResult.toUpperCase() === listProject[i]['firstName'].toUpperCase()||
                inputResult.toUpperCase() === listProject[i]['lastName'].toUpperCase()||
                inputResult.toUpperCase() === listProject[i]['age'].toUpperCase()||
                inputResult.toUpperCase() === listProject[i]['salary'].toUpperCase()+' '+listProject[i]['email'].toUpperCase()) {
                list.innerHTML+=`<tr>
                <td>${listProject[i]['firstName']}</td>
                <td>${listProject[i]['lastName']}</td>
                <td>${listProject[i]['age']}</td>
                <td>${listProject[i]['salary']}</td>
                <td>${listProject[i]['email']}</td>
                <td><a href="/messagePage/+${listProject[i]['id']}">Write to</a></td>
                </tr>
                `;}
        }
        if(list.innerHTML==='')
            searchResults.innerHTML+='empty';

    }
}

async function generateSearchForAdmin() {
    let token = localStorage.getItem('token');
    let inputResult_origin = document.getElementById('searchEmployee').value;
    let inputResult=inputResult_origin;
    if(inputResult_origin.length>0){
        inputResult = inputResult_origin[0].toUpperCase() + inputResult_origin.slice(1);}
    let list = document.getElementById('allEmployees');
    list.innerHTML = '';
    if(inputResult.length===0){
        await loadEmployeesForAdmin();
    }else{
        let listProject = await getAllEmployeesForAdmin(token);
        let searchResults=document.getElementById('searchResults');
        list.innerHTML+=`<tr>
        <th>Name</th>
        <th>Surname</th>
        <th>Age</th>
        <th>Salary</th>
        <th>Email</th>
    </tr>`;
        for (let i = 0; i < listProject.length; i++) {
            if (inputResult.toUpperCase() === listProject[i]['firstName'].toUpperCase()||
                inputResult.toUpperCase() === listProject[i]['lastName'].toUpperCase()||
                inputResult.toUpperCase() === listProject[i]['age'].toUpperCase()||
                inputResult.toUpperCase() === listProject[i]['salary'].toUpperCase()+' '+listProject[i]['email'].toUpperCase()){
                list.innerHTML+=`<tr>
                <td>${listProject[i]['firstName']}</td>
                <td>${listProject[i]['lastName']}</td>
                <td>${listProject[i]['age']}</td>
                <td>${listProject[i]['salary']}</td>
                <td>${listProject[i]['email']}</td>
                <td><a href="/updateemployeeAdmin/+${listProject[i]['id']}">Edit</a></td>
                <td><button onclick="adminDeleteEmployee(${listProject[i]['id']})">Delete</button></td>
                </tr>`;}
        }
        if(list.innerHTML==='')
            searchResults.innerHTML+='empty';

    }
}

//--------------------------------------------------------------------------------------------------------------------
async function onIndexLoad() {
    let token = localStorage.getItem('token');
    if (await isAuth()) {
        if (await isAdmin()) {
            await loadAdminIndex();
            await loadEmployeesForAdmin();
            await loadUsersForAdmin();
        } else {
            await loadUserIndex();
            await loadEmployees();
        }
    } else {
        //genLogReg(result);
    }
}

async function generateUserSearchForAdmin(){
    let token = localStorage.getItem('token');
    let inputResult_origin = document.getElementById('searchUserAdmin').value;
    let inputResult=inputResult_origin;
    if(inputResult_origin.length>0){
        inputResult = inputResult_origin[0].toUpperCase() + inputResult_origin.slice(1);}
    let list = document.getElementById('allUsers');
    list.innerHTML = '';
    if(inputResult.length===0){
        await loadUsersForAdmin();
    }else{
        let listProject = await getAllUsers();
        list.innerHTML+=`<tr>
        <th>User</th>
        <th>Password</th>
        <th>Email</th>
        <th>Role</th>
    </tr>`;
        for (let i = 0; i < listProject.length; i++) {
            if (inputResult.toUpperCase() === listProject[i]['username'].toUpperCase()||
                inputResult.toUpperCase() === listProject[i]['password'].toUpperCase()||
                inputResult.toUpperCase() === listProject[i]['email'].toUpperCase()+' '+listProject[i]['role'].toUpperCase())
            {
                list.innerHTML+=`<tr>
                <td>${listProject[i]['username']}</td>
                <td>${listProject[i]['password']}</td>
                <td>${listProject[i]['email']}</td>
                <td>${listProject[i].role.name}</td>
                <td><a href="/updateuserAdmin/+${listProject[i]['id']}">Edit</a></td>
                <td><button onclick="adminDeleteUser(${listProject[i]['id']})">Delete</button></td>
                </tr>`;}
        }
    }
}

//--------------------------
async function loadUserIndex(){
    let hrefs=document.getElementById('refsForUser');
    let myProfileHref= await a('profile', 'My profile');
    let myEmployeesHrefs=await a('addemployee','Add employee');
    let search=document.getElementById('search');
    let searchButton=await button( generateSearch,'Search');
    hrefs.appendChild(myProfileHref);
    hrefs.appendChild(await br());
    hrefs.appendChild(myEmployeesHrefs);
    hrefs.appendChild(await br());
    search.appendChild(searchButton);
}

async function loadAdminIndex(){
    let search=document.getElementById('search');
    let searchUser=document.getElementById('searchUser');
    searchUser.innerHTML=`<input type="text" id="searchUserAdmin" placeholder="Login/email">`;
    let employeeContainer=document.getElementById('allEmployeesContainer');
    let userContainer=document.getElementById('allUsersContainer');
    let searchButton=await button( generateSearchForAdmin,'Search');
    let searchUserButton=await button( generateUserSearchForAdmin,'Search');
    let createHref=await a( 'addemployeeadmin','Add employee');
    let createHrefUser=await a( 'addemployeeadmin','Add user');
    search.appendChild(searchButton);
    searchUser.appendChild(searchUserButton);
    employeeContainer.appendChild(createHref);
    userContainer.appendChild(createHrefUser);

}


async function loadEmployees(){
    let token = localStorage.getItem('token');
    let list = document.getElementById('allEmployees');
    list.innerHTML = '';
    let listProject = await getAllEmployees(token);
    list.innerHTML+=`<tr>
         <th>Name</th>
        <th>Surname</th>
        <th>Age</th>
        <th>Salary</th>
        <th>Email</th>
    </tr>`;
    for (let i = 0; i < listProject.length; i++) {
        list.innerHTML+=`<tr>
               <td>${listProject[i]['firstName']}</td>
                <td>${listProject[i]['lastName']}</td>
                <td>${listProject[i]['age']}</td>
                <td>${listProject[i]['salary']}</td>
                <td>${listProject[i]['email']}</td>
                <td><a href="/messagePage/+${listProject[i]['id']}">Write to</a></td>
                 </tr>
    `;}
}


async function loadEmployeesForAdmin(){
    let token = localStorage.getItem('token');
    let list = document.getElementById('allEmployees');
    list.innerHTML = '';

    let listProject = await getAllEmployeesForAdmin(token);
    list.innerHTML+=`<tr>
        <th>Name</th>
        <th>Surname</th>
        <th>Age</th>
        <th>Salary</th>
        <th>Email</th>
    </tr>`;
    for (let i = 0; i < listProject.length; i++) {
        list.innerHTML+=`<tr>
               <td>${listProject[i]['firstName']}</td>
                <td>${listProject[i]['lastName']}</td>
                <td>${listProject[i]['age']}</td>
                <td>${listProject[i]['salary']}</td>
                <td>${listProject[i]['email']}</td>
                <td><a href="/updateemployeeAdmin/+${listProject[i]['id']}">Edit</a></td>
                <td><button onclick="adminDeleteEmployee(${listProject[i]['id']})">Delete</button></td>
                </tr>
    `;}
}


async function loadUsersForAdmin(){
    let token = localStorage.getItem('token');
    let list = document.getElementById('allUsers');
    list.innerHTML = '';
    let listProject = await getAllUsers();
    list.innerHTML+=`<tr>
        <th>User</th>
        <th>Password</th>
        <th>Email</th>
        <th>Role</th>
    </tr>`;
    for (let i = 0; i < listProject.length; i++) {
        list.innerHTML+=`<tr>
                <td>${listProject[i]['username']}</td>
                <td>${listProject[i]['password']}</td>
                <td>${listProject[i]['email']}</td>
                <td>${listProject[i].role.name}</td>
                <td><button onclick='adminDeleteUser(${listProject[i]['id']})'>Delete</button></td>
                </tr>
    `;}
}





function rateChangeOption(){
    let rateSelect = document.getElementById('rateFilter');
    let selectedOption = rateSelect.options[rateSelect.selectedIndex];
    if(selectedOption.label==='Сначала низкие'){
        let sortedRows = Array.from(allEmployees.rows)
            .slice(1)
            .sort((rowA, rowB) => rowA.cells[5].innerHTML > rowB.cells[5].innerHTML ? 1 : -1);

        allEmployees.tBodies[0].append(sortedRows);
    }
}