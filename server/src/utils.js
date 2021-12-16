export function toPlainObj(model) {
  if (model instanceof Array) {
    return model.map(m => toPlainObj(m));
  } else {
    return model.get({ plain: true });
  }
}

export function toUpperCase(value) {
  if (value.toUpperCase().charCodeAt(0) > "M".charCodeAt(0))
    return value.toUpperCase();
  else return value.toLowerCase();
}
