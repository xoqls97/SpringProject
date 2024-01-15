console.log('boardRegister js in');

document.getElementById('trigger').addEventListener('click', () => {
    document.getElementById('file').click();
})

//실행파일, 이미지파일,
const regExp = new RegExp("\.(exe|sh|bat|dll|jar|msi)$");
const maxSize = 1024 * 1024 * 20; // 파일 최대 크기

function fileValidation(fileName, fileSize) {
    if (regExp.test(fileName)) {
        return 0;

    } else if (fileSize > maxSize) {
        return 0;
    } else {
        return 1;
    }
}


document.addEventListener('change', (e) => {
    console.log(e.target);
    if (e.target.id == 'file') { //파일에 변화가 생긴다면...
        // inpuyt file element 에 저자오딘 file의 정보를 가져오는 property
        const fileObj = document.getElementById('file').file;
        console.log(fileObj);

        //한번 disabled되면 혼자 풀어질 수 없음/ 버튼을 우너래 상태로 복구해줘야함
        document.getElementById('regBtn').disabled = false;
        //첨부파일에 대한 정보를fileZone에 기록
        let div = document.getElementById('fileZone');
        div.innerHTML = '';
        // ul >li로 값을 추가
        
        //여러 파일에 대한 검증ㅇ르 모두 통과하기 위해서
        //곱하기 로 각 파일마다 통과 여부를 확인
        let isOk = 1;

        let ul = `<ul class="list-group list-group-flush">`;
        for (let file of fileObj) {
            let vailResult = fileValidation(file.name, file.size);
            isOk *= vailResult;
            ul += `<li class="list-group-item">`;
            ul += `<div class="mb-3">`;
            ul += `${vailResult ? '<div class="fw-bold"> 업로드 가능</div>' : '<div class="fw-bold text-danger"> 업로드 불가능</div>'}`;
            ul += `${file.name}</div>`;
            ul += `<span class="badge rounded-pill text-bg-${vailResult ? 'success' : 'danger'}">${file.size} Byte</span>`;
            ul += `</li>`;
        }
        ul += `</li>`;
        div.innerHTML = ul;

        if (isOk == 0) {
            //하나의 파일이라도 검증에 통과하지 못한다면 버튼 비활성화.
            document.getElementById('regBtn').disabled = true;
        }

    }
});