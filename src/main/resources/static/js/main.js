let inp = document.querySelector('#currency');
let frm = document.querySelector('.search');
frm.action =frm.action.substring(0,frm.action.lastIndexOf('/')+1)+"1"

inp.addEventListener('change', (event) => {
  let temp=frm.action.substring(0,frm.action.lastIndexOf('/')+1)
  frm.action =temp+inp.value
});