function isNullOrWhiteSpaceCheck(feild) {
  let arg = feild === undefined ? this.toString() : feild;
  if (arg === undefined || arg === null) {
    return false;
  } else {
    let isWhiteSpace = /^\s*$/;
    if (typeof arg != 'string') {
      throw "Property or Arguments was not 'String' Types";
    }
    return !isWhiteSpace.test(arg.trim());
  }
}

function isLengthCheck(feild) {
  if (feild === undefined || feild === null || feild === '') {
    return false;
  } else if (feild.length > 50) {
    return false;
  } else if (feild.length === 0) {
    return false;
  }

  return true;
}

function isEmailCheck(feild) {
  const re =
    /^([\w._-])*[a-zA-Z0-9]+([\w._-])*([a-zA-Z0-9])+([\w._-])+@([a-zA-Z0-9]+.)+[a-zA-Z0-9]{2,8}$/;
  return re.test(String(feild).toLowerCase());
}

function isPhoneCheck(feild) {
  let target = feild.replaceAll('-', '');
  const re = /[(0-9)]{8,13}/;
  return re.test(String(target));
}

function isTextCheck(feild) {
  const reEng = /[a-zA-Z]/;
  const reSpc = /[~!@#$%^&*()_+|<>?:{}]/;
  const reKor = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;

  if (feild === undefined || feild === null || feild === '') {
    return false;
  } else if (reEng.test(feild) || reSpc.test(feild) || reKor.test(feild)) {
    return false;
  } else {
    return true;
  }
}

function isSpecialTextCheck(feild) {
  const reSpc = /[~!@#$%^&*()_+|<>?:{}]/;

  if (feild === undefined || feild === null || feild === '') {
    return false;
  } else if (reSpc.test(feild)) {
    return false;
  } else {
    return true;
  }
}

export {
  isNullOrWhiteSpaceCheck,
  isLengthCheck,
  isEmailCheck,
  isPhoneCheck,
  isTextCheck,
  isSpecialTextCheck,
};
