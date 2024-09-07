import * as React from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';

export default function TokenForm({onSubmit}) {
  const [open, setOpen] = React.useState(false);
  const [token, setToken] = React.useState('');

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handle = (value) =>{
    setToken(value);
    onSubmit(token);
    setToken('');
    //handleClose();
  }

  return (
    <React.Fragment>
      <Button variant="outlined" onClick={handleClickOpen}>
        Get All Orders
      </Button>
      <Dialog
        open={open}
        onClose={handleClose}
        PaperProps={{
          component: 'form',
          onSubmit: (event) => {
            event.preventDefault();
            const formData = new FormData(event.currentTarget);
            const formJson = Object.fromEntries(formData.entries());
            const token_input = formJson.token;
            onSubmit(token_input);
            handleClose();
          },
        }}
      >
        <DialogTitle>Token Submit</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Enter your token
          </DialogContentText>
          <TextField
            autoFocus
            required
            margin="dense"
            id="token"
            name="token"
            label="Token"
            type="token"
            fullWidth
            variant="standard"
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>Cancel</Button>
          <Button type="submit">Submit</Button>
        </DialogActions>
      </Dialog>
    </React.Fragment>
  );
}