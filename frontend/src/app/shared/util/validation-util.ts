import {AbstractControl, FormGroup} from '@angular/forms';

export class ValidationUtil {
  private static KEYS = {
    required: 'Campo obrigatório',
    '_default': 'Campo inválido'
  };

  public static getErrorKeys(control: AbstractControl): string {
    let messages;
    if ((control.dirty || control.touched) && control.errors) {
      Object.entries(control.errors).forEach(
        ([key, _]) => {
          messages.concat(this.KEYS[key] || this.KEYS['_default']);
        }
      );
    }
    return messages;
  }

  public static validateForm(form: FormGroup) {
    (<any>Object).values(form.controls).forEach(control => {
      control.markAsTouched();

      if (control.controls) {
        this.validateForm(control);
      }
    });
  }
}
