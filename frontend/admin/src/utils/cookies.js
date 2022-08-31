function saveAuthToCookie(value) {
  document.cookie = `t_admin_auth=${value}`;
}

function saveUserToCookie(data) {
  document.cookie = `t_admin_user_email=${data.email}`;
  document.cookie = `t_admin_user_name=${data.name}`;
  document.cookie = `t_admin_user_id=${data.id}`;
}

function getAuthFromCookie() {
  return document.cookie.replace(
    /(?:(?:^|.*;\s*)t_admin_auth\s*=\s*([^;]*).*$)|^.*$/,
    '$1',
  );
}

function getUserEmailFromCookie() {
  return document.cookie.replace(
    /(?:(?:^|.*;\s*)t_admin_user_email\s*=\s*([^;]*).*$)|^.*$/,
    '$1',
  );
}

function getUserNameFromCookie() {
  return document.cookie.replace(
    /(?:(?:^|.*;\s*)t_admin_user_name\s*=\s*([^;]*).*$)|^.*$/,
    '$1',
  );
}

function getUserIdFromCookie() {
  return document.cookie.replace(
    /(?:(?:^|.*;\s*)t_admin_user_id\s*=\s*([^;]*).*$)|^.*$/,
    '$1',
  );
}

function deleteCookie(value) {
  document.cookie = `${value}=; expires=Thu, 01 Jan 1970 00:00:01 GMT;`;
}

export {
  saveAuthToCookie,
  saveUserToCookie,
  getAuthFromCookie,
  getUserEmailFromCookie,
  getUserNameFromCookie,
  getUserIdFromCookie,
  deleteCookie,
};
