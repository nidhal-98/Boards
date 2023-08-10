var myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

var boardTitle = "Sprint Board 2023.1";

// First, make a GET request to check if the board already exists
var getOptions = {
    method: 'GET',
    headers: myHeaders,
    redirect: 'follow'
};

fetch("http://localhost:8080/api/boards?title=" + encodeURIComponent(boardTitle), getOptions)
    .then(response => response.json())
    .then(data => {
        // Assuming your API returns an array of boards matching the title
        if (data.length > 0) {
            console.log("Board already exists:", data[0]);
        } else {
            // If the board doesn't exist, proceed with the POST request
            var raw = JSON.stringify({
                "title": boardTitle
            });

            var postOptions = {
                method: 'POST',
                headers: myHeaders,
                body: raw,
                redirect: 'follow'
            };

            fetch("http://localhost:8080/api/boards", postOptions)
                .then(response => response.text())
                .then(result => console.log(result))
                .catch(error => console.log('error', error));
        }
    })
    .catch(error => console.log('error', error));



function getCards() {
    var requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };

    fetch("http://localhost:8080/api/boards/1/cards", requestOptions)
        .then(response => response.json())
        .then(parsedResponse => {
            parsedResponse.forEach(element => {

                let boardDiv;

                let sectionDiv = element.section;

                if (sectionDiv === 1) {
                    boardDiv = document.getElementById("ToDoBoard1");
                }
                else if (sectionDiv === 2) {
                    boardDiv = document.getElementById("InProgress");
                }
                else {
                    boardDiv = document.getElementById("Done");
                }

                let cardDiv = document.createElement("div");
                cardDiv.className = "card";


                let idDiv = document.createElement("div");
                idDiv.className = "card-id";
                idDiv.id = element.id;
                idDiv.textContent = "#" + element.id; // Set the text content here

                let cardTitleDiv = document.createElement("div");
                cardTitleDiv.className = "card-title";
                cardTitleDiv.id = "cardTitle-" + element.id;
                cardTitleDiv.textContent = element.title; // Set the text content here

                let descriptionDiv = document.createElement("div");
                descriptionDiv.className = "card-description";
                descriptionDiv.id = "cardDescription-" + element.id;
                descriptionDiv.textContent = element.description; // Set the text content here

                let goCornner = document.createElement("div");
                goCornner.className = "go-corner";

                let goArrow = document.createElement("div");
                goArrow.className = "go-arrow";
                goArrow.textContent = "➷";

                goCornner.appendChild(goArrow);

                cardDiv.appendChild(idDiv);
                cardDiv.appendChild(cardTitleDiv);
                cardDiv.appendChild(descriptionDiv);
                cardDiv.appendChild(goCornner);

                boardDiv.appendChild(cardDiv);
            });
        })
        .catch(error => console.log('error', error));
}

window.onload = getCards;

function handledropdownUpdateItemClick(sectionValueUpadte) {
    selectedSectionUpdate = sectionValueUpadte;
    document.getElementById("dropdownMenuButtonUpdate").textContent = "Section " + selectedSectionUpdate; // Update the button text to reflect the selected section
}

let cardId;
fetch("http://localhost:8080/api/boards/1/cards")
    .then((response) => { return response.json() })
    .then((parsedResponse) => {
        let dropdownMenuDiv = document.getElementById("dropdownMenu2");

        parsedResponse.forEach((element) => {
            let dropdownItem = document.createElement("a");
            dropdownItem.className = "dropdown-item";
            dropdownItem.textContent = element.id;
            dropdownItem.setAttribute("data-id", element.id);
            dropdownMenuDiv.appendChild(dropdownItem);

            dropdownItem.addEventListener("click", function () {
                cardId = this.getAttribute("data-id");
                document.getElementById("dropdownMenuButton2").innerHTML = "Card " + cardId;
            });
        });
    })
    .catch((error) => {
        console.error("Error fetching data:", error);
    });

function deleteData() {
    const url = "http://localhost:8080/api/boards/1/cards/" + cardId;

    const options = {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    };

    fetch(url, options)
        .then(response => {
            if (response.ok) {
                console.log('Player deleted successfully!');
                location.reload();
            } else {
                console.error('Error deleting player:', response.statusText);
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
    location.reload();
}

function updateCard() {
    let cardTitleUpdate;
    let cardDescriptionUpdate;

    if (document.getElementById("updatedTitle").value === "") {
        cardTitleUpdate = document.getElementById("cardTitle-" + cardId).textContent;
    }
    else {
        cardTitleUpdate = document.getElementById("updatedTitle").value;
    }

    if (document.getElementById("updatedDescription").value === "") {
        cardDescriptionUpdate = document.getElementById("cardDescription-" + cardId).textContent;
    }
    else {
        cardDescriptionUpdate = document.getElementById("updatedDescription").value;
    }

    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var raw = JSON.stringify({
        "title": cardTitleUpdate,
        "description": cardDescriptionUpdate,
        "section": selectedSectionUpdate
    });

    var requestOptions = {
        method: 'PUT',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };

    fetch("http://localhost:8080/api/boards/1/cards/" + cardId, requestOptions)
        .then(response => response.text())
        .then(result => {
            console.log(result);
            location.reload();
        })
        .catch(error => console.log('error', error));
    location.reload();
}

document.getElementById("section1-update").addEventListener("click", () => handledropdownUpdateItemClick(1));
document.getElementById("section2-update").addEventListener("click", () => handledropdownUpdateItemClick(2));
document.getElementById("section3-update").addEventListener("click", () => handledropdownUpdateItemClick(3));

// Variable to store the selected section
let selectedSection = 1; // Set the default selected section to 1

// Function to handle the click event on dropdown items and update the selected section
function handleDropdownItemClick(sectionValue) {
    selectedSection = sectionValue;
    document.getElementById("dropdownMenuButton").textContent = "Section " + selectedSection; // Update the button text to reflect the selected section
}

function createCard() {
    let cardTitle = document.getElementById("enterTitleCard").value;
    let cardDescription = document.getElementById("enterDescriptionCard").value;

    // Validate cardTitle, cardDescription, and selectedSection here if needed

    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var raw = JSON.stringify({
        "title": cardTitle,
        "description": cardDescription,
        "section": selectedSection
    });

    var requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };

    fetch("http://localhost:8080/api/boards/1/cards", requestOptions)
        .then(response => response.json())
        .then(result => {
            console.log(result);
            location.reload();
        })
        .catch(error => console.log('error', error));
    location.reload();
}

// Add event listeners to dropdown items and update selected section on click
document.getElementById("section1").addEventListener("click", () => handleDropdownItemClick(1));
document.getElementById("section2").addEventListener("click", () => handleDropdownItemClick(2));
document.getElementById("section3").addEventListener("click", () => handleDropdownItemClick(3));

// Function to fetch the titles from the API
function getTitlesFromAPI() {

    var requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };

    fetch("http://localhost:8080/api/boards", requestOptions)
        .then((response) => { return response.json() })
        .then(
            (parsedResponse) => {

                parsedResponse.forEach(element => {
                    let titleDiv = document.getElementById("main-title");
                    titleDiv.innerHTML = element.title;
                });
            }
        )
        .catch(error => console.log('error', error));
}

window.addEventListener("load", function () {
    getTitlesFromAPI();
});

function upadteTitle() {
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    const titleValue = document.getElementById('input-title').value;

    var raw = JSON.stringify({
        "title": titleValue // Changed 'nameValue' to 'titleValue'
    });

    var requestOptions = {
        method: 'PUT',
        headers: myHeaders,
        body: raw,
    };

    fetch("http://localhost:8080/api/boards/1", requestOptions)
        .then(response => response.json())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
}

const inputTitle = document.getElementById("input-title");
const mainTitle = document.getElementById("main-title");
const setButton = document.getElementById("set-button");

// Event listener for the "SET" button click
setButton.addEventListener("click", function () {
    upadteTitle();
    // Get the value from the input text box
    const newTitle = inputTitle.value.trim();

    // Update the main title with the new value
    if (newTitle !== "") {
        mainTitle.textContent = newTitle;
    }
});