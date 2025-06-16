fetch('/auth/me')
  .then(res => {
    if (!res.ok) throw new Error("로그인 필요");
    return res.json();
  })
  .then(user => {
    const header = document.createElement("header");
    header.style.display = "flex";
    header.style.justifyContent = "space-between";
    header.style.alignItems = "center";
    header.style.padding = "10px";

    header.innerHTML = `
      <a href="/main">
        <img src="/images/LAS_logo.png" alt="LAS 로고" style="height: 50px; cursor: pointer;">
      </a>
      <div style="font-size: 16px;">
        <span style="font-weight: bold;">${user.name}</span>
        <a href="/profile" style="margin-left: 10px;">마이프로필</a>
        <a href="#" onclick="logout()" style="margin-left: 10px;">로그아웃</a>
      </div>
    `;

    document.body.insertBefore(header, document.body.firstChild);
  })
  .catch(() => {
  });

function logout() {
  fetch('/auth/logout', { method: 'POST' })
    .then(() => {
      alert("로그아웃 되었습니다.");
      window.location.href = "/login";
    });
}
