

```graphql
mutation {
    saveEmployee(input: {
        id: "001" , 
        empName: "Alice",
        jobName: "Engineer",
        hireDate: "2025-10-12T14:30:00"
        salary: 85000
    }) {
        id
        jobName
        salary
        hireDate
    }
}
```


```graphql
query {
    employeeById(id: "001") {
    id
    empName
    salary
    jobName
  }
}
```