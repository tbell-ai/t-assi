function saveAuthToCookie(value) {
  document.cookie = `t_customer_auth=${value}`;
}

function saveUserToCookie(data) {
  document.cookie = `t_customer_user_email=${data.email}`;
  document.cookie = `t_customer_user_name=${data.name}`;
  document.cookie = `t_customer_user_id=${data.id}`;
  document.cookie = `t_customer_user_company=${data.company}`;
}

function saveProjectIdToCookie(value) {
  document.cookie = `t_current_project_id=${value}`;
}

// function saveUserLoginIdInfo(id) {
//   document.cookie = id
// }

function getAuthFromCookie() {
  return document.cookie.replace(
    /(?:(?:^|.*;\s*)t_customer_auth\s*=\s*([^;]*).*$)|^.*$/,
    '$1',
  );
}

function getUserEmailFromCookie() {
  return document.cookie.replace(
    /(?:(?:^|.*;\s*)t_customer_user_email\s*=\s*([^;]*).*$)|^.*$/,
    '$1',
  );
}

function getUserNameFromCookie() {
  return document.cookie.replace(
    /(?:(?:^|.*;\s*)t_customer_user_name\s*=\s*([^;]*).*$)|^.*$/,
    '$1',
  );
}

function getUserIdFromCookie() {
  return document.cookie.replace(
    /(?:(?:^|.*;\s*)t_customer_user_id\s*=\s*([^;]*).*$)|^.*$/,
    '$1',
  );
}

function getUserCompanyFromCookie() {
  return document.cookie.replace(
    /(?:(?:^|.*;\s*)t_customer_user_company\s*=\s*([^;]*).*$)|^.*$/,
    '$1',
  );
}

function getProjectIdFromCookie() {
  return document.cookie.replace(
    /(?:(?:^|.*;\s*)t_current_project_id\s*=\s*([^;]*).*$)|^.*$/,
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
  getUserCompanyFromCookie,
  deleteCookie,
  saveProjectIdToCookie,
  getProjectIdFromCookie,
};
