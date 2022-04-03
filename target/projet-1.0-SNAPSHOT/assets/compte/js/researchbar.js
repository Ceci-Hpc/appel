// const clearInput = () => {
//     const input = document.getElementsByTagName("input")[0];
//     input.value = "";
//
//
// }
//
// const clearBtn = document.getElementById("clear-btn");
// clearBtn.addEventListener("click", clearInput);


const findByPrenom = (event) => {

    let tableBody = document.getElementById("table-body")
    let prenomEtudiant = event.target.value.trim()
    tableBody.innerHTML = ''
    if (prenomEtudiant !== '') {

        let xhr = new XMLHttpRequest()
        // Requête au serveur avec les paramètres éventuels.
        xhr.open("POST", "/rechercher-etudiant", true)
        xhr.onload = () => {
            if (xhr.status === 200) {
                let etudiants = JSON.parse(xhr.responseText)
                tableBody.innerHTML = etudiants.body

            }
        }

        // Encodage de l'url
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        // Envoie de la requête.
        //xhr.send(params);
        xhr.send(`prenomEtudiant=${prenomEtudiant}`);

    }
}

document.getElementById("recherche").addEventListener("keyup", findByPrenom);