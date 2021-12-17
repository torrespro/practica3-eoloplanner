$(document).ready(function () {
    getAll();
    $('#btnFetch').click(function () {
        $('#btnFetch').html('<span id="spinner" class="spinner-border spinner-border-sm mr-2" role="status" aria-hidden="true"></span> Loading...').prop('disabled', true)
        insert($("#fname").val());
    });
});


async function getAll() {
    const query = "{ getEolicPlants {id city planning} }";
    const response = await fetch('http://localhost:3000/graphql', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
        },
        body: JSON.stringify({ query })
    });

    let result = await response.json();    
    let list = $("#myList").empty();

    result.data.getEolicPlants.forEach((item)=>{
        list.append('<li class="list-group-item">'+item.city + " " + item.planning+'</li>');
    })
    
    $("#spinner").hide();
    $("#btnFetch").prop("disabled",false);
    $("#btnFetch").text("Add");
    $("#fname").val("").focus();
}


async function insert(city)  {
    var query = `mutation CreateEolicPlant($EolicPlantInput: EolicPlantInput) {
      createEolicPlant(eolicPlantInput: $EolicPlantInput) {
        city
      }
    }`;

    const response = await fetch('http://localhost:3000/graphql', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
        },
        body: JSON.stringify({
            query,
            variables: {
                EolicPlantInput: {
                    city,
              }
            }
          }),
    });

    await getAll();
}
