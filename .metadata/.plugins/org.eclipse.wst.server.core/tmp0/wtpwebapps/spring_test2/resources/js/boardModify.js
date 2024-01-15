console.log("boardModify js in");

document.addEventListener('click', (e) => {
    console.log(e.target);
    if (e.target.classList.contains('file-x')) {
        let button = e.target.closest('button');
        let uuid = button.dataset.uuid;
        console.log(uuid);
        deleteFileToServer(uuid).then(result => {
        console.log(result);
            if (result == "1") {
                alert('파일 삭제 성공');
                e.target.closest('li').remove();

            }
        })
    }
})


async function deleteFileToServer(uuid) {
    try {
        const url = '/board/deletefile'
        const config = {
            method: "delete",
            headers: {
                'content-type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify({uuid:uuid})
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

